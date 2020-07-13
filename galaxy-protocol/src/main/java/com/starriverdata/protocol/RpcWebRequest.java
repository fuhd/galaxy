// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: web_request.proto

package com.starriverdata.protocol;

public final class RpcWebRequest {
  private RpcWebRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface WebRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:WebRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>sint32 rid = 1;</code>
     */
    int getRid();

    /**
     * <code>.WebOperate operate = 2;</code>
     */
    int getOperateValue();
    /**
     * <code>.WebOperate operate = 2;</code>
     */
    RpcWebOperate.WebOperate getOperate();

    /**
     * <code>.ExecuteKind ek = 3;</code>
     */
    int getEkValue();
    /**
     * <code>.ExecuteKind ek = 3;</code>
     */
    JobExecuteKind.ExecuteKind getEk();

    /**
     * <pre>
     * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
     * </pre>
     *
     * <code>string id = 4;</code>
     */
    java.lang.String getId();
    /**
     * <pre>
     * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
     * </pre>
     *
     * <code>string id = 4;</code>
     */
    com.google.protobuf.ByteString
        getIdBytes();

    /**
     * <code>string executor = 5;</code>
     */
    java.lang.String getExecutor();
    /**
     * <code>string executor = 5;</code>
     */
    com.google.protobuf.ByteString
        getExecutorBytes();

    /**
     * <code>bytes body = 6;</code>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code WebRequest}
   */
  public  static final class WebRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:WebRequest)
      WebRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use WebRequest.newBuilder() to construct.
    private WebRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private WebRequest() {
      rid_ = 0;
      operate_ = 0;
      ek_ = 0;
      id_ = "";
      executor_ = "";
      body_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private WebRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              rid_ = input.readSInt32();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();

              operate_ = rawValue;
              break;
            }
            case 24: {
              int rawValue = input.readEnum();

              ek_ = rawValue;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              id_ = s;
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              executor_ = s;
              break;
            }
            case 50: {

              body_ = input.readBytes();
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RpcWebRequest.internal_static_WebRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcWebRequest.internal_static_WebRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcWebRequest.WebRequest.class, RpcWebRequest.WebRequest.Builder.class);
    }

    /**
     * Protobuf enum {@code WebRequest.Trigger}
     */
    public enum Trigger
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>MANUAL = 0;</code>
       */
      MANUAL(0),
      /**
       * <code>MANUAL_RECOVER = 1;</code>
       */
      MANUAL_RECOVER(1),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>MANUAL = 0;</code>
       */
      public static final int MANUAL_VALUE = 0;
      /**
       * <code>MANUAL_RECOVER = 1;</code>
       */
      public static final int MANUAL_RECOVER_VALUE = 1;


      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Trigger valueOf(int value) {
        return forNumber(value);
      }

      public static Trigger forNumber(int value) {
        switch (value) {
          case 0: return MANUAL;
          case 1: return MANUAL_RECOVER;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Trigger>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Trigger> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Trigger>() {
              public Trigger findValueByNumber(int number) {
                return Trigger.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return RpcWebRequest.WebRequest.getDescriptor().getEnumTypes().get(0);
      }

      private static final Trigger[] VALUES = values();

      public static Trigger valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private Trigger(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:WebRequest.Trigger)
    }

    public static final int RID_FIELD_NUMBER = 1;
    private int rid_;
    /**
     * <code>sint32 rid = 1;</code>
     */
    public int getRid() {
      return rid_;
    }

    public static final int OPERATE_FIELD_NUMBER = 2;
    private int operate_;
    /**
     * <code>.WebOperate operate = 2;</code>
     */
    public int getOperateValue() {
      return operate_;
    }
    /**
     * <code>.WebOperate operate = 2;</code>
     */
    public RpcWebOperate.WebOperate getOperate() {
      @SuppressWarnings("deprecation")
      RpcWebOperate.WebOperate result = RpcWebOperate.WebOperate.valueOf(operate_);
      return result == null ? RpcWebOperate.WebOperate.UNRECOGNIZED : result;
    }

    public static final int EK_FIELD_NUMBER = 3;
    private int ek_;
    /**
     * <code>.ExecuteKind ek = 3;</code>
     */
    public int getEkValue() {
      return ek_;
    }
    /**
     * <code>.ExecuteKind ek = 3;</code>
     */
    public JobExecuteKind.ExecuteKind getEk() {
      @SuppressWarnings("deprecation")
      JobExecuteKind.ExecuteKind result = JobExecuteKind.ExecuteKind.valueOf(ek_);
      return result == null ? JobExecuteKind.ExecuteKind.UNRECOGNIZED : result;
    }

    public static final int ID_FIELD_NUMBER = 4;
    private volatile java.lang.Object id_;
    /**
     * <pre>
     * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
     * </pre>
     *
     * <code>string id = 4;</code>
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
     * </pre>
     *
     * <code>string id = 4;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EXECUTOR_FIELD_NUMBER = 5;
    private volatile java.lang.Object executor_;
    /**
     * <code>string executor = 5;</code>
     */
    public java.lang.String getExecutor() {
      java.lang.Object ref = executor_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        executor_ = s;
        return s;
      }
    }
    /**
     * <code>string executor = 5;</code>
     */
    public com.google.protobuf.ByteString
        getExecutorBytes() {
      java.lang.Object ref = executor_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        executor_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BODY_FIELD_NUMBER = 6;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>bytes body = 6;</code>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (rid_ != 0) {
        output.writeSInt32(1, rid_);
      }
      if (operate_ != RpcWebOperate.WebOperate.UpdateJob.getNumber()) {
        output.writeEnum(2, operate_);
      }
      if (ek_ != JobExecuteKind.ExecuteKind.ScheduleKind.getNumber()) {
        output.writeEnum(3, ek_);
      }
      if (!getIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, id_);
      }
      if (!getExecutorBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, executor_);
      }
      if (!body_.isEmpty()) {
        output.writeBytes(6, body_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (rid_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeSInt32Size(1, rid_);
      }
      if (operate_ != RpcWebOperate.WebOperate.UpdateJob.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, operate_);
      }
      if (ek_ != JobExecuteKind.ExecuteKind.ScheduleKind.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, ek_);
      }
      if (!getIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, id_);
      }
      if (!getExecutorBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, executor_);
      }
      if (!body_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, body_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof RpcWebRequest.WebRequest)) {
        return super.equals(obj);
      }
      RpcWebRequest.WebRequest other = (RpcWebRequest.WebRequest) obj;

      boolean result = true;
      result = result && (getRid()
          == other.getRid());
      result = result && operate_ == other.operate_;
      result = result && ek_ == other.ek_;
      result = result && getId()
          .equals(other.getId());
      result = result && getExecutor()
          .equals(other.getExecutor());
      result = result && getBody()
          .equals(other.getBody());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RID_FIELD_NUMBER;
      hash = (53 * hash) + getRid();
      hash = (37 * hash) + OPERATE_FIELD_NUMBER;
      hash = (53 * hash) + operate_;
      hash = (37 * hash) + EK_FIELD_NUMBER;
      hash = (53 * hash) + ek_;
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
      hash = (37 * hash) + EXECUTOR_FIELD_NUMBER;
      hash = (53 * hash) + getExecutor().hashCode();
      hash = (37 * hash) + BODY_FIELD_NUMBER;
      hash = (53 * hash) + getBody().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RpcWebRequest.WebRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcWebRequest.WebRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcWebRequest.WebRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcWebRequest.WebRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static RpcWebRequest.WebRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcWebRequest.WebRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(RpcWebRequest.WebRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code WebRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:WebRequest)
        RpcWebRequest.WebRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcWebRequest.internal_static_WebRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcWebRequest.internal_static_WebRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcWebRequest.WebRequest.class, RpcWebRequest.WebRequest.Builder.class);
      }

      // Construct using com.dfire.protocol.RpcWebRequest.WebRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        rid_ = 0;

        operate_ = 0;

        ek_ = 0;

        id_ = "";

        executor_ = "";

        body_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcWebRequest.internal_static_WebRequest_descriptor;
      }

      @java.lang.Override
      public RpcWebRequest.WebRequest getDefaultInstanceForType() {
        return RpcWebRequest.WebRequest.getDefaultInstance();
      }

      @java.lang.Override
      public RpcWebRequest.WebRequest build() {
        RpcWebRequest.WebRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public RpcWebRequest.WebRequest buildPartial() {
        RpcWebRequest.WebRequest result = new RpcWebRequest.WebRequest(this);
        result.rid_ = rid_;
        result.operate_ = operate_;
        result.ek_ = ek_;
        result.id_ = id_;
        result.executor_ = executor_;
        result.body_ = body_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof RpcWebRequest.WebRequest) {
          return mergeFrom((RpcWebRequest.WebRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcWebRequest.WebRequest other) {
        if (other == RpcWebRequest.WebRequest.getDefaultInstance()) return this;
        if (other.getRid() != 0) {
          setRid(other.getRid());
        }
        if (other.operate_ != 0) {
          setOperateValue(other.getOperateValue());
        }
        if (other.ek_ != 0) {
          setEkValue(other.getEkValue());
        }
        if (!other.getId().isEmpty()) {
          id_ = other.id_;
          onChanged();
        }
        if (!other.getExecutor().isEmpty()) {
          executor_ = other.executor_;
          onChanged();
        }
        if (other.getBody() != com.google.protobuf.ByteString.EMPTY) {
          setBody(other.getBody());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        RpcWebRequest.WebRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (RpcWebRequest.WebRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int rid_ ;
      /**
       * <code>sint32 rid = 1;</code>
       */
      public int getRid() {
        return rid_;
      }
      /**
       * <code>sint32 rid = 1;</code>
       */
      public Builder setRid(int value) {
        
        rid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>sint32 rid = 1;</code>
       */
      public Builder clearRid() {
        
        rid_ = 0;
        onChanged();
        return this;
      }

      private int operate_ = 0;
      /**
       * <code>.WebOperate operate = 2;</code>
       */
      public int getOperateValue() {
        return operate_;
      }
      /**
       * <code>.WebOperate operate = 2;</code>
       */
      public Builder setOperateValue(int value) {
        operate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.WebOperate operate = 2;</code>
       */
      public RpcWebOperate.WebOperate getOperate() {
        @SuppressWarnings("deprecation")
        RpcWebOperate.WebOperate result = RpcWebOperate.WebOperate.valueOf(operate_);
        return result == null ? RpcWebOperate.WebOperate.UNRECOGNIZED : result;
      }
      /**
       * <code>.WebOperate operate = 2;</code>
       */
      public Builder setOperate(RpcWebOperate.WebOperate value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        operate_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.WebOperate operate = 2;</code>
       */
      public Builder clearOperate() {
        
        operate_ = 0;
        onChanged();
        return this;
      }

      private int ek_ = 0;
      /**
       * <code>.ExecuteKind ek = 3;</code>
       */
      public int getEkValue() {
        return ek_;
      }
      /**
       * <code>.ExecuteKind ek = 3;</code>
       */
      public Builder setEkValue(int value) {
        ek_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.ExecuteKind ek = 3;</code>
       */
      public JobExecuteKind.ExecuteKind getEk() {
        @SuppressWarnings("deprecation")
        JobExecuteKind.ExecuteKind result = JobExecuteKind.ExecuteKind.valueOf(ek_);
        return result == null ? JobExecuteKind.ExecuteKind.UNRECOGNIZED : result;
      }
      /**
       * <code>.ExecuteKind ek = 3;</code>
       */
      public Builder setEk(JobExecuteKind.ExecuteKind value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        ek_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.ExecuteKind ek = 3;</code>
       */
      public Builder clearEk() {
        
        ek_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object id_ = "";
      /**
       * <pre>
       * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
       * </pre>
       *
       * <code>string id = 4;</code>
       */
      public java.lang.String getId() {
        java.lang.Object ref = id_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          id_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
       * </pre>
       *
       * <code>string id = 4;</code>
       */
      public com.google.protobuf.ByteString
          getIdBytes() {
        java.lang.Object ref = id_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          id_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
       * </pre>
       *
       * <code>string id = 4;</code>
       */
      public Builder setId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
       * </pre>
       *
       * <code>string id = 4;</code>
       */
      public Builder clearId() {
        
        id_ = getDefaultInstance().getId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * update 时 id=actionId  source=ScheduleJob时 id=historyId  source=DebugJob时id=debugId
       * </pre>
       *
       * <code>string id = 4;</code>
       */
      public Builder setIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        id_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object executor_ = "";
      /**
       * <code>string executor = 5;</code>
       */
      public java.lang.String getExecutor() {
        java.lang.Object ref = executor_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          executor_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string executor = 5;</code>
       */
      public com.google.protobuf.ByteString
          getExecutorBytes() {
        java.lang.Object ref = executor_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          executor_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string executor = 5;</code>
       */
      public Builder setExecutor(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        executor_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string executor = 5;</code>
       */
      public Builder clearExecutor() {
        
        executor_ = getDefaultInstance().getExecutor();
        onChanged();
        return this;
      }
      /**
       * <code>string executor = 5;</code>
       */
      public Builder setExecutorBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        executor_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes body = 6;</code>
       */
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      /**
       * <code>bytes body = 6;</code>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bytes body = 6;</code>
       */
      public Builder clearBody() {
        
        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:WebRequest)
    }

    // @@protoc_insertion_point(class_scope:WebRequest)
    private static final RpcWebRequest.WebRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RpcWebRequest.WebRequest();
    }

    public static RpcWebRequest.WebRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<WebRequest>
        PARSER = new com.google.protobuf.AbstractParser<WebRequest>() {
      @java.lang.Override
      public WebRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new WebRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<WebRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<WebRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public RpcWebRequest.WebRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_WebRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_WebRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021web_request.proto\032\021web_operate.proto\032\022" +
      "execute_kind.proto\"\250\001\n\nWebRequest\022\013\n\003rid" +
      "\030\001 \001(\021\022\034\n\007operate\030\002 \001(\0162\013.WebOperate\022\030\n\002" +
      "ek\030\003 \001(\0162\014.ExecuteKind\022\n\n\002id\030\004 \001(\t\022\020\n\010ex" +
      "ecutor\030\005 \001(\t\022\014\n\004body\030\006 \001(\014\")\n\007Trigger\022\n\n" +
      "\006MANUAL\020\000\022\022\n\016MANUAL_RECOVER\020\001B%\n\022com.dfi" +
      "re.protocolB\rRpcWebRequestH\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          RpcWebOperate.getDescriptor(),
          JobExecuteKind.getDescriptor(),
        }, assigner);
    internal_static_WebRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_WebRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_WebRequest_descriptor,
        new java.lang.String[] { "Rid", "Operate", "Ek", "Id", "Executor", "Body", });
    RpcWebOperate.getDescriptor();
    JobExecuteKind.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}