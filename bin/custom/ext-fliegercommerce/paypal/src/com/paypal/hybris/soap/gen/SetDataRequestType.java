
package com.paypal.hybris.soap.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SetDataRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetDataRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BillingApprovalDetails" type="{urn:ebay:apis:eBLBaseComponents}BillingApprovalDetailsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BuyerDetail" type="{urn:ebay:apis:eBLBaseComponents}BuyerDetailType" minOccurs="0"/>
 *         &lt;element name="InfoSharingDirectives" type="{urn:ebay:apis:eBLBaseComponents}InfoSharingDirectivesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetDataRequestType", propOrder = {
    "billingApprovalDetails",
    "buyerDetail",
    "infoSharingDirectives"
})
public class SetDataRequestType {

    @XmlElement(name = "BillingApprovalDetails")
    protected List<BillingApprovalDetailsType> billingApprovalDetails;
    @XmlElement(name = "BuyerDetail")
    protected BuyerDetailType buyerDetail;
    @XmlElement(name = "InfoSharingDirectives")
    protected InfoSharingDirectivesType infoSharingDirectives;

    /**
     * Gets the value of the billingApprovalDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billingApprovalDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingApprovalDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillingApprovalDetailsType }
     * 
     * 
     */
    public List<BillingApprovalDetailsType> getBillingApprovalDetails() {
        if (billingApprovalDetails == null) {
            billingApprovalDetails = new ArrayList<BillingApprovalDetailsType>();
        }
        return this.billingApprovalDetails;
    }

    /**
     * Gets the value of the buyerDetail property.
     * 
     * @return
     *     possible object is
     *     {@link BuyerDetailType }
     *     
     */
    public BuyerDetailType getBuyerDetail() {
        return buyerDetail;
    }

    /**
     * Sets the value of the buyerDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuyerDetailType }
     *     
     */
    public void setBuyerDetail(BuyerDetailType value) {
        this.buyerDetail = value;
    }

    /**
     * Gets the value of the infoSharingDirectives property.
     * 
     * @return
     *     possible object is
     *     {@link InfoSharingDirectivesType }
     *     
     */
    public InfoSharingDirectivesType getInfoSharingDirectives() {
        return infoSharingDirectives;
    }

    /**
     * Sets the value of the infoSharingDirectives property.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoSharingDirectivesType }
     *     
     */
    public void setInfoSharingDirectives(InfoSharingDirectivesType value) {
        this.infoSharingDirectives = value;
    }

}
