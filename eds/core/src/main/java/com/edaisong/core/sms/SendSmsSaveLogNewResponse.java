
package com.edaisong.core.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SendSmsSaveLogNewResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendSmsSaveLogNewResult"
})
@XmlRootElement(name = "SendSmsSaveLogNewResponse")
public class SendSmsSaveLogNewResponse {

    @XmlElement(name = "SendSmsSaveLogNewResult")
    protected String sendSmsSaveLogNewResult;

    /**
     * 获取sendSmsSaveLogNewResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendSmsSaveLogNewResult() {
        return sendSmsSaveLogNewResult;
    }

    /**
     * 设置sendSmsSaveLogNewResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendSmsSaveLogNewResult(String value) {
        this.sendSmsSaveLogNewResult = value;
    }

}
