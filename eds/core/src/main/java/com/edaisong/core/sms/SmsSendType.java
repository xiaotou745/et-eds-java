
package com.edaisong.core.sms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SmsSendType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="SmsSendType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="亿美软通"/&gt;
 *     &lt;enumeration value="营销渠道"/&gt;
 *     &lt;enumeration value="天润融通"/&gt;
 *     &lt;enumeration value="百分通联"/&gt;
 *     &lt;enumeration value="云信"/&gt;
 *     &lt;enumeration value="梦网"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SmsSendType")
@XmlEnum
public enum SmsSendType {

    亿美软通,
    营销渠道,
    天润融通,
    百分通联,
    云信,
    梦网;

    public String value() {
        return name();
    }

    public static SmsSendType fromValue(String v) {
        return valueOf(v);
    }

}
