package com.unkur.affnetui.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_orders")
public class Order {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private long id = 0;
		
		@Column(name = "distributor_id")
		private String distributor_id = null;
		
		@Column(name = "sub_id")
	    private int sub_id = 0;
		
		@Column(name = "click_id")
		private long click_id = 0L;

		@Column(name = "status")
		private String status = null;
		
		@Column(name = "price_original")
		private double price_original = 0.0;
		
		@Column(name = "currency_original_id")
		private int currency_original_id = 1;
		
		@Column(name = "price_common")
	    private double price_common = 0.0;
		
		@Column(name = "title")
	    private String title = null;
			
		@Column(name = "created_at")
		private long created_at = 0;
		
		@Column(name = "updated_at")
		private long updated_at = 0;

		public Order() {}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getDistributor_id() {
			return distributor_id;
		}

		public void setDistributor_id(String distributor_id) {
			this.distributor_id = distributor_id;
		}

		public int getSub_id() {
			return sub_id;
		}

		public void setSub_id(int sub_id) {
			this.sub_id = sub_id;
		}

		public long getClick_id() {
			return click_id;
		}

		public void setClick_id(long click_id) {
			this.click_id = click_id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public double getPrice_original() {
			return price_original;
		}

		public void setPrice_original(double price_original) {
			this.price_original = price_original;
		}

		public int getCurrency_original_id() {
			return currency_original_id;
		}

		public void setCurrency_original_id(int currency_original_id) {
			this.currency_original_id = currency_original_id;
		}

		public double getPrice_common() {
			return price_common;
		}

		public void setPrice_common(double price_common) {
			this.price_common = price_common;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public long getCreated_at() {
			return created_at;
		}

		public void setCreated_at(long created_at) {
			this.created_at = created_at;
		}

		public long getUpdated_at() {
			return updated_at;
		}

		public void setUpdated_at(long updated_at) {
			this.updated_at = updated_at;
		}

		@Override
		public String toString() {
			return "\n Order [id=" + id + ", title=" + title + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Order other = (Order) obj;
			if (id != other.id)
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}
		
		
		
}
