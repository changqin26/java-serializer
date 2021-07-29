//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2021.07.19 um 12:09:30 PM CEST 
//


package org.opcfoundation.ua._2008._02.types;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.kscs.util.jaxb.Buildable;
import com.kscs.util.jaxb.PropertyTree;
import com.kscs.util.jaxb.PropertyTreeUse;


/**
 * <p>Java-Klasse für TransferSubscriptionsRequest complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TransferSubscriptionsRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RequestHeader" type="{http://opcfoundation.org/UA/2008/02/Types.xsd}RequestHeader" minOccurs="0"/&gt;
 *         &lt;element name="SubscriptionIds" type="{http://opcfoundation.org/UA/2008/02/Types.xsd}ListOfUInt32" minOccurs="0"/&gt;
 *         &lt;element name="SendInitialValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferSubscriptionsRequest", propOrder = {
    "requestHeader",
    "subscriptionIds",
    "sendInitialValues"
})
public class TransferSubscriptionsRequest {

    @XmlElementRef(name = "RequestHeader", namespace = "http://opcfoundation.org/UA/2008/02/Types.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<RequestHeader> requestHeader;
    @XmlElementRef(name = "SubscriptionIds", namespace = "http://opcfoundation.org/UA/2008/02/Types.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<ListOfUInt32> subscriptionIds;
    @XmlElement(name = "SendInitialValues")
    protected Boolean sendInitialValues;

    /**
     * Ruft den Wert der requestHeader-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}
     *     
     */
    public JAXBElement<RequestHeader> getRequestHeader() {
        return requestHeader;
    }

    /**
     * Legt den Wert der requestHeader-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}
     *     
     */
    public void setRequestHeader(JAXBElement<RequestHeader> value) {
        this.requestHeader = value;
    }

    /**
     * Ruft den Wert der subscriptionIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ListOfUInt32 }{@code >}
     *     
     */
    public JAXBElement<ListOfUInt32> getSubscriptionIds() {
        return subscriptionIds;
    }

    /**
     * Legt den Wert der subscriptionIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ListOfUInt32 }{@code >}
     *     
     */
    public void setSubscriptionIds(JAXBElement<ListOfUInt32> value) {
        this.subscriptionIds = value;
    }

    /**
     * Ruft den Wert der sendInitialValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSendInitialValues() {
        return sendInitialValues;
    }

    /**
     * Legt den Wert der sendInitialValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSendInitialValues(Boolean value) {
        this.sendInitialValues = value;
    }

    /**
     * Copies all state of this object to a builder. This method is used by the {@link
     * #copyOf} method and should not be called directly by client code.
     * 
     * @param _other
     *     A builder instance to which the state of this object will be copied.
     */
    public<_B >void copyTo(final TransferSubscriptionsRequest.Builder<_B> _other) {
        _other.requestHeader = this.requestHeader;
        _other.subscriptionIds = this.subscriptionIds;
        _other.sendInitialValues = this.sendInitialValues;
    }

    public<_B >TransferSubscriptionsRequest.Builder<_B> newCopyBuilder(final _B _parentBuilder) {
        return new TransferSubscriptionsRequest.Builder<_B>(_parentBuilder, this, true);
    }

    public TransferSubscriptionsRequest.Builder<Void> newCopyBuilder() {
        return newCopyBuilder(null);
    }

    public static TransferSubscriptionsRequest.Builder<Void> builder() {
        return new TransferSubscriptionsRequest.Builder<Void>(null, null, false);
    }

    public static<_B >TransferSubscriptionsRequest.Builder<_B> copyOf(final TransferSubscriptionsRequest _other) {
        final TransferSubscriptionsRequest.Builder<_B> _newBuilder = new TransferSubscriptionsRequest.Builder<_B>(null, null, false);
        _other.copyTo(_newBuilder);
        return _newBuilder;
    }

    /**
     * Copies all state of this object to a builder. This method is used by the {@link
     * #copyOf} method and should not be called directly by client code.
     * 
     * @param _other
     *     A builder instance to which the state of this object will be copied.
     */
    public<_B >void copyTo(final TransferSubscriptionsRequest.Builder<_B> _other, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        final PropertyTree requestHeaderPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("requestHeader"));
        if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(requestHeaderPropertyTree!= null):((requestHeaderPropertyTree == null)||(!requestHeaderPropertyTree.isLeaf())))) {
            _other.requestHeader = this.requestHeader;
        }
        final PropertyTree subscriptionIdsPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("subscriptionIds"));
        if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(subscriptionIdsPropertyTree!= null):((subscriptionIdsPropertyTree == null)||(!subscriptionIdsPropertyTree.isLeaf())))) {
            _other.subscriptionIds = this.subscriptionIds;
        }
        final PropertyTree sendInitialValuesPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("sendInitialValues"));
        if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(sendInitialValuesPropertyTree!= null):((sendInitialValuesPropertyTree == null)||(!sendInitialValuesPropertyTree.isLeaf())))) {
            _other.sendInitialValues = this.sendInitialValues;
        }
    }

    public<_B >TransferSubscriptionsRequest.Builder<_B> newCopyBuilder(final _B _parentBuilder, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        return new TransferSubscriptionsRequest.Builder<_B>(_parentBuilder, this, true, _propertyTree, _propertyTreeUse);
    }

    public TransferSubscriptionsRequest.Builder<Void> newCopyBuilder(final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        return newCopyBuilder(null, _propertyTree, _propertyTreeUse);
    }

    public static<_B >TransferSubscriptionsRequest.Builder<_B> copyOf(final TransferSubscriptionsRequest _other, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        final TransferSubscriptionsRequest.Builder<_B> _newBuilder = new TransferSubscriptionsRequest.Builder<_B>(null, null, false);
        _other.copyTo(_newBuilder, _propertyTree, _propertyTreeUse);
        return _newBuilder;
    }

    public static TransferSubscriptionsRequest.Builder<Void> copyExcept(final TransferSubscriptionsRequest _other, final PropertyTree _propertyTree) {
        return copyOf(_other, _propertyTree, PropertyTreeUse.EXCLUDE);
    }

    public static TransferSubscriptionsRequest.Builder<Void> copyOnly(final TransferSubscriptionsRequest _other, final PropertyTree _propertyTree) {
        return copyOf(_other, _propertyTree, PropertyTreeUse.INCLUDE);
    }

    public static class Builder<_B >implements Buildable
    {

        protected final _B _parentBuilder;
        protected final TransferSubscriptionsRequest _storedValue;
        private JAXBElement<RequestHeader> requestHeader;
        private JAXBElement<ListOfUInt32> subscriptionIds;
        private Boolean sendInitialValues;

        public Builder(final _B _parentBuilder, final TransferSubscriptionsRequest _other, final boolean _copy) {
            this._parentBuilder = _parentBuilder;
            if (_other!= null) {
                if (_copy) {
                    _storedValue = null;
                    this.requestHeader = _other.requestHeader;
                    this.subscriptionIds = _other.subscriptionIds;
                    this.sendInitialValues = _other.sendInitialValues;
                } else {
                    _storedValue = _other;
                }
            } else {
                _storedValue = null;
            }
        }

        public Builder(final _B _parentBuilder, final TransferSubscriptionsRequest _other, final boolean _copy, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
            this._parentBuilder = _parentBuilder;
            if (_other!= null) {
                if (_copy) {
                    _storedValue = null;
                    final PropertyTree requestHeaderPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("requestHeader"));
                    if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(requestHeaderPropertyTree!= null):((requestHeaderPropertyTree == null)||(!requestHeaderPropertyTree.isLeaf())))) {
                        this.requestHeader = _other.requestHeader;
                    }
                    final PropertyTree subscriptionIdsPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("subscriptionIds"));
                    if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(subscriptionIdsPropertyTree!= null):((subscriptionIdsPropertyTree == null)||(!subscriptionIdsPropertyTree.isLeaf())))) {
                        this.subscriptionIds = _other.subscriptionIds;
                    }
                    final PropertyTree sendInitialValuesPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("sendInitialValues"));
                    if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(sendInitialValuesPropertyTree!= null):((sendInitialValuesPropertyTree == null)||(!sendInitialValuesPropertyTree.isLeaf())))) {
                        this.sendInitialValues = _other.sendInitialValues;
                    }
                } else {
                    _storedValue = _other;
                }
            } else {
                _storedValue = null;
            }
        }

        public _B end() {
            return this._parentBuilder;
        }

        protected<_P extends TransferSubscriptionsRequest >_P init(final _P _product) {
            _product.requestHeader = this.requestHeader;
            _product.subscriptionIds = this.subscriptionIds;
            _product.sendInitialValues = this.sendInitialValues;
            return _product;
        }

        /**
         * Setzt den neuen Wert der Eigenschaft "requestHeader" (Vorher zugewiesener Wert
         * wird ersetzt)
         * 
         * @param requestHeader
         *     Neuer Wert der Eigenschaft "requestHeader".
         */
        public TransferSubscriptionsRequest.Builder<_B> withRequestHeader(final JAXBElement<RequestHeader> requestHeader) {
            this.requestHeader = requestHeader;
            return this;
        }

        /**
         * Setzt den neuen Wert der Eigenschaft "subscriptionIds" (Vorher zugewiesener Wert
         * wird ersetzt)
         * 
         * @param subscriptionIds
         *     Neuer Wert der Eigenschaft "subscriptionIds".
         */
        public TransferSubscriptionsRequest.Builder<_B> withSubscriptionIds(final JAXBElement<ListOfUInt32> subscriptionIds) {
            this.subscriptionIds = subscriptionIds;
            return this;
        }

        /**
         * Setzt den neuen Wert der Eigenschaft "sendInitialValues" (Vorher zugewiesener
         * Wert wird ersetzt)
         * 
         * @param sendInitialValues
         *     Neuer Wert der Eigenschaft "sendInitialValues".
         */
        public TransferSubscriptionsRequest.Builder<_B> withSendInitialValues(final Boolean sendInitialValues) {
            this.sendInitialValues = sendInitialValues;
            return this;
        }

        @Override
        public TransferSubscriptionsRequest build() {
            if (_storedValue == null) {
                return this.init(new TransferSubscriptionsRequest());
            } else {
                return ((TransferSubscriptionsRequest) _storedValue);
            }
        }

        public TransferSubscriptionsRequest.Builder<_B> copyOf(final TransferSubscriptionsRequest _other) {
            _other.copyTo(this);
            return this;
        }

        public TransferSubscriptionsRequest.Builder<_B> copyOf(final TransferSubscriptionsRequest.Builder _other) {
            return copyOf(_other.build());
        }

    }

    public static class Select
        extends TransferSubscriptionsRequest.Selector<TransferSubscriptionsRequest.Select, Void>
    {


        Select() {
            super(null, null, null);
        }

        public static TransferSubscriptionsRequest.Select _root() {
            return new TransferSubscriptionsRequest.Select();
        }

    }

    public static class Selector<TRoot extends com.kscs.util.jaxb.Selector<TRoot, ?> , TParent >
        extends com.kscs.util.jaxb.Selector<TRoot, TParent>
    {

        private com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>> requestHeader = null;
        private com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>> subscriptionIds = null;
        private com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>> sendInitialValues = null;

        public Selector(final TRoot root, final TParent parent, final String propertyName) {
            super(root, parent, propertyName);
        }

        @Override
        public Map<String, PropertyTree> buildChildren() {
            final Map<String, PropertyTree> products = new HashMap<String, PropertyTree>();
            products.putAll(super.buildChildren());
            if (this.requestHeader!= null) {
                products.put("requestHeader", this.requestHeader.init());
            }
            if (this.subscriptionIds!= null) {
                products.put("subscriptionIds", this.subscriptionIds.init());
            }
            if (this.sendInitialValues!= null) {
                products.put("sendInitialValues", this.sendInitialValues.init());
            }
            return products;
        }

        public com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>> requestHeader() {
            return ((this.requestHeader == null)?this.requestHeader = new com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>>(this._root, this, "requestHeader"):this.requestHeader);
        }

        public com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>> subscriptionIds() {
            return ((this.subscriptionIds == null)?this.subscriptionIds = new com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>>(this._root, this, "subscriptionIds"):this.subscriptionIds);
        }

        public com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>> sendInitialValues() {
            return ((this.sendInitialValues == null)?this.sendInitialValues = new com.kscs.util.jaxb.Selector<TRoot, TransferSubscriptionsRequest.Selector<TRoot, TParent>>(this._root, this, "sendInitialValues"):this.sendInitialValues);
        }

    }

}
