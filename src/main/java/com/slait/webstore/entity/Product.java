package com.slait.webstore.entity;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.slait.webstore.validator.Category;
import com.slait.webstore.validator.ProductId;

@Entity
@Table(name="Product")
@XmlRootElement
public class Product implements Serializable {

	private static final long serialVersionUID = 6818337297966359020L;

	@Id
	@Column(name="ProductId")
	@Pattern(regexp="P[0-9]+", message=
			"{Pattern.Product.productId.validation}")
	@ProductId
	private String productId;
	
	@Column(name="ProductName")
	@Size(min=4, max=50, message=
			"{Size.Product.name.validation}")
	private String name;
	
	@Column(name="UnitPrice")
	@Min(value=0, message=
			"{Min.Product.unitPrice.validation}")
	@Digits(integer=8, fraction=2, message=
			"{Digits.Product.unitPrice.validation}")
	@NotNull(message=
			"{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Manufacturer")
	private String manufacturer;
	
	@Column(name="Category")
	@Size(min=1, max=50, message=
			"{Size.Product.category.validation}")
	@Category
	private String category;
	
	@Column(name="UnitsInStock")
	@Min(value=0, message=
			"{Min.Product.unitsInStock.validation}")
	private long unitsInStock;
	
	@Column(name="UnitsInOrder")
	private long unitsInOrder;
	
	@Column(name="ProductImage")
	@Lob
	private Blob productImage;
	
	@Column(name="ProductManual")
	@Lob
	private Blob productManual;
	
	public Product() {
		super();
	}
	
	public Product(String productId, String name, 
			BigDecimal unitPrice) {
		this.productId = productId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		System.out.println("in setunitsinstock");
		this.unitsInStock = unitsInStock;
		System.out.println("setunitsinstock() done");
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}
	
	@XmlTransient
	public Blob getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) throws IOException, SerialException, SQLException {
		System.out.println("in setProductImage");
		byte[] picBytes = productImage.getBytes();
		Blob imageBlob = new SerialBlob(picBytes);
		this.productImage = imageBlob;
	}
	
	@XmlTransient
	public Blob getProductManual() {
		return productManual;
	}

	public void setProductManual(MultipartFile productPdf) throws IOException, SerialException, SQLException {
		System.out.println("in setProductManual");
		byte[] manualBytes = productPdf.getBytes();
		Blob manualBlob = new SerialBlob(manualBytes);
		this.productManual = manualBlob;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) 
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)	
				return false;
		} else if (!productId.equals(other.productId)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result +
			((productId == null) ? 0 : productId.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId +
			", name=" + name + "]";
	}
	
}
