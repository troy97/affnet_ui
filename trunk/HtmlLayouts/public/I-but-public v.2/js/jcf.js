/*
 * JavaScript Custom Forms 1.2.3
 */
jcf = {
	// global options
	modules: {},
	plugins: {},
	baseOptions: {
		labelActiveClass: 'jcf-label-active',
		hiddenClass:'jcf-hidden',
		focusClass:'jcf-focus',
		wrapperTag: 'div'
	},
	// replacer function
	customForms: {
		replaceAll: function(obj, opt) {
			for(var k in jcf.modules) {
				var els = jcf.lib.queryBySelector(jcf.modules[k].prototype.selector);
				for(var i = 0; i<els.length; i++) {
					if(els[i].jcf) {
						// refresh form element state
						els[i].jcf.refreshState();
					} else {
						// replace form element
						new jcf.modules[k]({
							replaces:els[i]
						});
					}
				}
			}
		}
	},
	// detect device type
	isTouchDevice: (function() {
		try {
			document.createEvent("TouchEvent");
			return true;
		} catch (e) {
			return false;
		}
	}()),
	// define base module
	setBaseModule: function(obj) {
		jcf.customControl = function(opt){
			this.options = jcf.lib.extend({}, jcf.baseOptions, this.defaultOptions, opt);
			this.init();
		}
		for(var p in obj) {
			jcf.customControl.prototype[p] = obj[p];
		}
	},
	// add module to jcf.modules
	addModule: function(obj) {
		if(obj.name){
			// create new module proto class
			jcf.modules[obj.name] = function(){
				jcf.modules[obj.name].superclass.constructor.apply(this, arguments);
			}
			jcf.lib.inherit(jcf.modules[obj.name], jcf.customControl);
			for(var p in obj) {
				jcf.modules[obj.name].prototype[p] = obj[p]
			}
			// on create module
			jcf.modules[obj.name].prototype.onCreateModule();
			// make callback for exciting modules
			for(var mod in jcf.modules) {
				if(jcf.modules[mod] != jcf.modules[obj.name]) {
					jcf.modules[mod].prototype.onModuleAdded(jcf.modules[obj.name]);
				}
			}
		}
	},
	// add plugin to jcf.plugins
	addPlugin: function(obj) {
		if(obj && obj.name) {
			jcf.plugins[obj.name] = function() {
				this.init.apply(this, arguments);
			}
			for(var p in obj) {
				jcf.plugins[obj.name].prototype[p] = obj[p];
			}
		}
	},
	// miscellaneous init
	init: function(){
		this.eventPress = this.isTouchDevice ? 'touchstart' : 'mousedown';
		this.eventMove = this.isTouchDevice ? 'touchmove' : 'mousemove';
		this.eventRelease = this.isTouchDevice ? 'touchend' : 'mouseup';
		return this;
	}
}.init();

/*
 * Custom Form Control prototype
 */
jcf.setBaseModule({
	init: function(){
		if(this.options.replaces) {
			this.realElement = this.options.replaces;
			this.replaceObject();
		}
	},
	defaultOptions: {
		// default module options (will be merged with base options)
	},
	replaceObject: function(){
		this.createWrapper();
		this.attachEvents();
		this.setupWrapper();
		this.fixStyles();
	},
	createWrapper: function(){
		this.fakeElement = document.createElement(this.options.wrapperTag);
		this.fakeElement.jcf = this.realElement.jcf = this;
		this.labelFor = jcf.lib.getLabelFor(this.realElement);
		jcf.lib.disableTextSelection(this.fakeElement);
		jcf.lib.addClass(this.realElement, jcf.baseOptions.hiddenClass);
	},
	attachEvents: function(){
		jcf.lib.event.add(this.realElement, 'click', this.onClick, this);
		jcf.lib.event.add(this.realElement, 'focus', this.onFocus, this);
		jcf.lib.event.add(this.realElement, 'blur', this.onBlur, this);

		jcf.lib.event.add(this.fakeElement, 'click', this.onFakeClick, this);
		jcf.lib.event.add(this.fakeElement, 'mousedown', this.onFakeMouseDown, this);
		jcf.lib.event.add(this.fakeElement, 'mouseup', this.onFakeMouseUp, this);

		if(this.labelFor) {
			this.labelFor.jcf = this;
			jcf.lib.event.add(this.labelFor, 'click', this.onFakeClick, this);
			jcf.lib.event.add(this.labelFor, 'mousedown', this.onFakeMouseDown, this);
			jcf.lib.event.add(this.labelFor, 'mouseup', this.onFakeMouseUp, this);
		}
	},
	fixStyles: function() {
		// hide mobile webkit tap effect
		var tapStyle = 'rgba(255,255,255,0)';
		this.realElement.style.webkitTapHighlightColor = tapStyle; 
		this.fakeElement.style.webkitTapHighlightColor = tapStyle; 
		if(this.labelFor) {
			this.labelFor.style.webkitTapHighlightColor = tapStyle; 
		}
	},
	setupWrapper: function(){
		// implement in subclass
	},
	refreshState: function(){
		// implement in subclass
	},
	onClick: function(){
		this.refreshState();
	},
	onFocus: function(){
		if(jcf.focusedElement && jcf.focusedElement.realElement != this.realElement) {
			if(jcf.isTouchDevice) {
				jcf.focusedElement.onBlur();
			} 
			jcf.focusedElement.realElement.blur();
		}
		jcf.focusedElement = this;
		clearTimeout(this.focusTimer);
		this.fakeElement.mouseDownFlag = false;
		if(!this.realElement.disabled) {
			jcf.lib.addClass(this.fakeElement,this.options.focusClass);
		}
	},
	onBlur: function(cb){
		var _this = this;
		this.focusTimer = setTimeout(function(){
			if(!_this.fakeElement.mouseDownFlag) {
				jcf.lib.removeClass(_this.fakeElement,_this.options.focusClass);
				if(typeof cb === 'function') cb();
			}
		},10);
	},
	onFakeClick: function(){
		this.realElement.focus();
	},
	onFakeMouseDown: function(){
		this.fakeElement.mouseDownFlag = true;
		return false;
	},
	onFakeMouseUp: function(){
		this.fakeElement.mouseDownFlag = false;
	},
	onCreateModule: function(){
		// implement in subclass
	},
	onModuleAdded: function(module) {
		// implement in subclass
	},
	onControlReady: function() {
		// implement in subclass
	}
});

/*
 * JCF Utility Library
 */
jcf.lib = {
	bind: function(func, scope){
		return function() {
			return func.apply(scope, arguments);
		}
	},
	browser: (function() {
		var ua = navigator.userAgent.toLowerCase(), res = {},
		match = /(webkit)[ \/]([\w.]+)/.exec(ua) || /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) ||
				/(msie) ([\w.]+)/.exec(ua) || ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) || [];
		res[match[1]] = true;
		res.version = match[2] || "0";
		res.safariMac = ua.indexOf('mac') != -1 && ua.indexOf('safari') != -1;
		return res;
	})(),
	getOffset: function (obj) {
		if (obj.getBoundingClientRect) {
			var scrollLeft = window.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft;
			var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
			var clientLeft = document.documentElement.clientLeft || document.body.clientLeft || 0;
			var clientTop = document.documentElement.clientTop || document.body.clientTop || 0;
			return {
				top:Math.round(obj.getBoundingClientRect().top + scrollTop - clientTop),
				left:Math.round(obj.getBoundingClientRect().left + scrollLeft - clientLeft)
			}
		} else {
			var posLeft = 0, posTop = 0;
			while (obj.offsetParent) {posLeft += obj.offsetLeft; posTop += obj.offsetTop; obj = obj.offsetParent;}
			return {top:posTop,left:posLeft};
		}
	},
	getScrollTop: function() {
		return window.pageYOffset || document.documentElement.scrollTop;
	},
	getScrollLeft: function() {
		return window.pageXOffset || document.documentElement.scrollLeft;
	},
	getWindowWidth: function(){
		return document.compatMode=='CSS1Compat' ? document.documentElement.clientWidth : document.body.clientWidth;
	},
	getWindowHeight: function(){
		return document.compatMode=='CSS1Compat' ? document.documentElement.clientHeight : document.body.clientHeight;
	},
	getStyle: function(el, prop) {
		if (document.defaultView && document.defaultView.getComputedStyle) {
			return document.defaultView.getComputedStyle(el, null)[prop];
		} else if (el.currentStyle) {
			return el.currentStyle[prop];
		} else {
			return el.style[prop];
		}
	},
	getParent: function(obj, selector) {
		while(obj.parentNode && obj.parentNode != document.body) {
			if(obj.parentNode.tagName.toLowerCase() == selector.toLowerCase()) {
				return obj.parentNode;
			}
			obj = obj.parentNode;
		}
		return false;
	},
	isParent: function(child, parent) {
		while(child.parentNode) {
			if(child.parentNode === parent) {
				return true;
			}
			child = child.parentNode;
		}
		return false;
	},
	getLabelFor: function(object) {
		if(jcf.lib.getParent(object,'label')) {
			return object.parentNode;
		} else if(object.id) {
			return jcf.lib.queryBySelector('label[for=' + object.id + ']')[0];
		}
	},
	disableTextSelection: function(el){
		el.setAttribute('unselectable', 'on');
		el.style.MozUserSelect = 'none';
		el.style.WebkitUserSelect = 'none';
		el.style.UserSelect = 'none';
		el.onselectstart = function() {return false};
	},
	queryBySelector: function(selector, scope){
		return this.getElementsBySelector(selector, scope);
	},
	prevSibling: function(node) {
		while(node = node.previousSibling) if(node.nodeType == 1) break;
		return node;
	},
	nextSibling: function(node) {
		while(node = node.nextSibling) if(node.nodeType == 1) break;
		return node;
	},
	fireEvent: function(element,event) {
		if (document.createEventObject){
			var evt = document.createEventObject();
			return element.fireEvent('on'+event,evt)
		}
		else{
			var evt = document.createEvent('HTMLEvents');
			evt.initEvent(event, true, true );
			return !element.dispatchEvent(evt);
		}
	},
	isParent: function(p, c) {
		while(c.parentNode) {
			if(p == c) {
				return true;
			}
			c = c.parentNode;
		}
		return false;
	},
	inherit: function(Child, Parent) {
		var F = function() { }
		F.prototype = Parent.prototype
		Child.prototype = new F()
		Child.prototype.constructor = Child
		Child.superclass = Parent.prototype
	},
	extend: function(obj) {
		for(var i = 1; i < arguments.length; i++) {
			for(var p in arguments[i]) {
				if(arguments[i].hasOwnProperty(p)) {
					obj[p] = arguments[i][p];
				}
			}
		}
		return obj;
	},
	hasClass: function (obj,cname) {
		return (obj.className ? obj.className.match(new RegExp('(\\s|^)'+cname+'(\\s|$)')) : false);
	},
	addClass: function (obj,cname) {
		if (!this.hasClass(obj,cname)) obj.className += " "+cname;
	},
	removeClass: function (obj,cname) {
		if (this.hasClass(obj,cname)) obj.className=obj.className.replace(new RegExp('(\\s|^)'+cname+'(\\s|$)'),' ');
	},
	getAllClasses: function(cname, prefix, skip) {
		if(!skip) skip = '';
		if(!prefix) prefix = '';
		return cname ? cname.replace(new RegExp('(\\s|^)'+skip+'(\\s|$)'),' ').replace(/[\s]*([\S]+)+[\s]*/gi,prefix+"$1 ") : '';
	},
	getElementsBySelector: function(selector, scope) {
		if(typeof document.querySelectorAll === 'function') {
			return (scope || document).querySelectorAll(selector);
		}
		var tokens = selector.split(' ');
		var currentContext = [scope || document.body];
		for (var i = 0; i < tokens.length; i++) {
			token = tokens[i].replace(/^\s+/,'').replace(/\s+$/,'');
			if (token.indexOf('#') > -1) {
				var bits = token.split('#'), tagName = bits[0], id = bits[1];
				var element = document.getElementById(id);
				if (tagName && element.nodeName.toLowerCase() != tagName) {
					return [];
				}
				currentContext = [element];
				continue;
			}
			if (token.indexOf('.') > -1) {
				var bits = token.split('.'), tagName = bits[0] || '*', className = bits[1], found = [], foundCount = 0;
				for (var h = 0; h < currentContext.length; h++) {
					var elements;
					if (tagName == '*') {
						elements = currentContext[h].getElementsByTagName('*');
					} else {
						elements = currentContext[h].getElementsByTagName(tagName);
					}
					for (var j = 0; j < elements.length; j++) {
						found[foundCount++] = elements[j];
					}
				}
				currentContext = [];
				var currentContextIndex = 0;
				for (var k = 0; k < found.length; k++) {
					if (found[k].className && found[k].className.match(new RegExp('(\\s|^)'+className+'(\\s|$)'))) {
						currentContext[currentContextIndex++] = found[k];
					}
				}
				continue;
			}
			if (token.match(/^(\w*)\[(\w+)([=~\|\^\$\*]?)=?"?([^\]"]*)"?\]$/)) {
				var tagName = RegExp.$1 || '*', attrName = RegExp.$2, attrOperator = RegExp.$3, attrValue = RegExp.$4;
				if(attrName.toLowerCase() == 'for' && this.browser.msie && this.browser.version < 8) {
					attrName = 'htmlFor';
				}
				var found = [], foundCount = 0;
				for (var h = 0; h < currentContext.length; h++) {
					var elements;
					if (tagName == '*') {
						elements = currentContext[h].getElementsByTagName('*');
					} else {
						elements = currentContext[h].getElementsByTagName(tagName);
					}
					for (var j = 0; j < elements.length; j++) {
						found[foundCount++] = elements[j];
					}
				}
				currentContext = [];
				var currentContextIndex = 0, checkFunction;
				switch (attrOperator) {
					case '=': checkFunction = function(e) { return (e.getAttribute(attrName) == attrValue) }; break;
					case '~': checkFunction = function(e) { return (e.getAttribute(attrName).match(new RegExp('(\\s|^)'+attrValue+'(\\s|$)'))) }; break;
					case '|': checkFunction = function(e) { return (e.getAttribute(attrName).match(new RegExp('^'+attrValue+'-?'))) }; break;
					case '^': checkFunction = function(e) { return (e.getAttribute(attrName).indexOf(attrValue) == 0) }; break;
					case '$': checkFunction = function(e) { return (e.getAttribute(attrName).lastIndexOf(attrValue) == e.getAttribute(attrName).length - attrValue.length) }; break;
					case '*': checkFunction = function(e) { return (e.getAttribute(attrName).indexOf(attrValue) > -1) }; break;
					default : checkFunction = function(e) { return e.getAttribute(attrName) };
				}
				currentContext = [];
				var currentContextIndex = 0;
				for (var k = 0; k < found.length; k++) {
					if (checkFunction(found[k])) {
						currentContext[currentContextIndex++] = found[k];
					}
				}
				continue;
			}
			tagName = token;
			var found = [], foundCount = 0;
			for (var h = 0; h < currentContext.length; h++) {
				var elements = currentContext[h].getElementsByTagName(tagName);
				for (var j = 0; j < elements.length; j++) {
					found[foundCount++] = elements[j];
				}
			}
			currentContext = found;
		}
		return currentContext;
	},
	domReady: function (handler){
		var called = false
		function ready() {
			if (called) return;
			called = true;
			handler();
		}
		if (document.addEventListener) {
			document.addEventListener("DOMContentLoaded", ready, false);
		} else if (document.attachEvent) {
			if (document.documentElement.doScroll && window == window.top) {
				function tryScroll(){
					if (called) return
					if (!document.body) return
					try {
						document.documentElement.doScroll("left")
						ready()
					} catch(e) {
						setTimeout(tryScroll, 0)
					}
				}
				tryScroll()
			}
			document.attachEvent("onreadystatechange", function(){
				if (document.readyState === "complete") {
					ready()
				}
			})
		}
		if (window.addEventListener) window.addEventListener('load', ready, false)
		else if (window.attachEvent) window.attachEvent('onload', ready)
	},
	event: (function(){
		var guid = 0;
		function fixEvent(e) {
			e = e || window.event;
			if (e.isFixed) {
				return e;
			}
			e.isFixed = true; 
			e.preventDefault = e.preventDefault || function(){this.returnValue = false}
			e.stopPropagation = e.stopPropagaton || function(){this.cancelBubble = true}
			if (!e.target) {
				e.target = e.srcElement
			}
			if (!e.relatedTarget && e.fromElement) {
				e.relatedTarget = e.fromElement == e.target ? e.toElement : e.fromElement;
			}
			if (e.pageX == null && e.clientX != null) {
				var html = document.documentElement, body = document.body;
				e.pageX = e.clientX + (html && html.scrollLeft || body && body.scrollLeft || 0) - (html.clientLeft || 0);
				e.pageY = e.clientY + (html && html.scrollTop || body && body.scrollTop || 0) - (html.clientTop || 0);
			}
			if (!e.which && e.button) {
				e.which = e.button & 1 ? 1 : (e.button & 2 ? 3 : (e.button & 4 ? 2 : 0));
			}
			if(e.type === "DOMMouseScroll" || e.type === 'mousewheel') {
				e.mWheelDelta = 0;
				if (e.wheelDelta) {
					e.mWheelDelta = e.wheelDelta/120;
				} else if (e.detail) {
					e.mWheelDelta = -e.detail/3;
				}
			}
			return e;
		}
		function commonHandle(event, customScope) {
			event = fixEvent(event);
			var handlers = this.events[event.type];
			for (var g in handlers) {
				var handler = handlers[g];
				var ret = handler.call(customScope || this, event);
				if (ret === false) {
					event.preventDefault()
					event.stopPropagation()
				}
			}
		}
		var publicAPI = {
			add: function(elem, type, handler, forcedScope) {
				if (elem.setInterval && (elem != window && !elem.frameElement)) {
					elem = window;
				}
				if (!handler.guid) {
					handler.guid = ++guid;
				}
				if (!elem.events) {
					elem.events = {};
					elem.handle = function(event) {
						return commonHandle.call(elem, event);
					}
				}
				if (!elem.events[type]) {
					elem.events[type] = {};
					if (elem.addEventListener) elem.addEventListener(type, elem.handle, false);
					else if (elem.attachEvent) elem.attachEvent("on" + type, elem.handle);
					if(type === 'mousewheel') {
						publicAPI.add(elem, 'DOMMouseScroll', handler, forcedScope);
					}
				}
				var fakeHandler = jcf.lib.bind(handler, forcedScope);
				fakeHandler.guid = handler.guid;
				elem.events[type][handler.guid] = forcedScope ? fakeHandler : handler;
			},
			remove: function(elem, type, handler) {
				var handlers = elem.events && elem.events[type];
				if (!handlers) return;
				delete handlers[handler.guid];
				for(var any in handlers) return;
				if (elem.removeEventListener) elem.removeEventListener(type, elem.handle, false);
				else if (elem.detachEvent) elem.detachEvent("on" + type, elem.handle);
				delete elem.events[type];
				for (var any in elem.events) return;
				try {
					delete elem.handle;
					delete elem.events;
				} catch(e) {
					if(elem.removeAttribute) {
						elem.removeAttribute("handle");
						elem.removeAttribute("events");
					}
				}
				if(type === 'mousewheel') {
					publicAPI.remove(elem, 'DOMMouseScroll', handler);
				}
			}
		}
		return publicAPI;
	}())
}