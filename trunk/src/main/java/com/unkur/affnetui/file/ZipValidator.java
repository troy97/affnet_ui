package com.unkur.affnetui.file;

import org.apache.commons.fileupload.MultipartStream;

import com.unkur.affnetui.ValidationException;

@FileExtension(".zip")
public class ZipValidator extends AbstractFileValidator {

	@Override
	public byte[] validateDownload(MultipartStream stream) throws ValidationException {
		//ZIP download cannot be checked, so it's always valid
		return new byte[0];
	}

}
