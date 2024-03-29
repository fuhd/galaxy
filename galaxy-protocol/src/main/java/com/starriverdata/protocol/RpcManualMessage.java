// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: manual_message.proto

package com.starriverdata.protocol;

public final class RpcManualMessage {
  private RpcManualMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ManualMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ManualMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string historyId = 1;</code>
     */
    java.lang.String getHistoryId();
    /**
     * <code>string historyId = 1;</code>
     */
    com.google.protobuf.ByteString
        getHistoryIdBytes();

    /**
     * <code>sint32 exitCode = 2;</code>
     */
    int getExitCode();
  }
  /**
   * Protobuf type {@code ManualMessage}
   */
  public  static final class ManualMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ManualMessage)
      ManualMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ManualMessage.newBuilder() to construct.
    private ManualMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ManualMessage() {
      historyId_ = "";
      exitCode_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ManualMessage(
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
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              historyId_ = s;
              break;
            }
            case 16: {

              exitCode_ = input.readSInt32();
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
      return RpcManualMessage.internal_static_ManualMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcManualMessage.internal_static_ManualMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcManualMessage.ManualMessage.class, RpcManualMessage.ManualMessage.Builder.class);
    }

    public static final int HISTORYID_FIELD_NUMBER = 1;
    private volatile java.lang.Object historyId_;
    /**
     * <code>string historyId = 1;</code>
     */
    public java.lang.String getHistoryId() {
      java.lang.Object ref = historyId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        historyId_ = s;
        return s;
      }
    }
    /**
     * <code>string historyId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getHistoryIdBytes() {
      java.lang.Object ref = historyId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        historyId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EXITCODE_FIELD_NUMBER = 2;
    private int exitCode_;
    /**
     * <code>sint32 exitCode = 2;</code>
     */
    public int getExitCode() {
      return exitCode_;
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
      if (!getHistoryIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, historyId_);
      }
      if (exitCode_ != 0) {
        output.writeSInt32(2, exitCode_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getHistoryIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, historyId_);
      }
      if (exitCode_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeSInt32Size(2, exitCode_);
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
      if (!(obj instanceof RpcManualMessage.ManualMessage)) {
        return super.equals(obj);
      }
      RpcManualMessage.ManualMessage other = (RpcManualMessage.ManualMessage) obj;

      boolean result = true;
      result = result && getHistoryId()
          .equals(other.getHistoryId());
      result = result && (getExitCode()
          == other.getExitCode());
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
      hash = (37 * hash) + HISTORYID_FIELD_NUMBER;
      hash = (53 * hash) + getHistoryId().hashCode();
      hash = (37 * hash) + EXITCODE_FIELD_NUMBER;
      hash = (53 * hash) + getExitCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RpcManualMessage.ManualMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcManualMessage.ManualMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcManualMessage.ManualMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcManualMessage.ManualMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static RpcManualMessage.ManualMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcManualMessage.ManualMessage parseFrom(
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
    public static Builder newBuilder(RpcManualMessage.ManualMessage prototype) {
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
     * Protobuf type {@code ManualMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ManualMessage)
        RpcManualMessage.ManualMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcManualMessage.internal_static_ManualMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcManualMessage.internal_static_ManualMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcManualMessage.ManualMessage.class, RpcManualMessage.ManualMessage.Builder.class);
      }

      // Construct using com.dfire.protocol.RpcManualMessage.ManualMessage.newBuilder()
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
        historyId_ = "";

        exitCode_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcManualMessage.internal_static_ManualMessage_descriptor;
      }

      @java.lang.Override
      public RpcManualMessage.ManualMessage getDefaultInstanceForType() {
        return RpcManualMessage.ManualMessage.getDefaultInstance();
      }

      @java.lang.Override
      public RpcManualMessage.ManualMessage build() {
        RpcManualMessage.ManualMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public RpcManualMessage.ManualMessage buildPartial() {
        RpcManualMessage.ManualMessage result = new RpcManualMessage.ManualMessage(this);
        result.historyId_ = historyId_;
        result.exitCode_ = exitCode_;
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
        if (other instanceof RpcManualMessage.ManualMessage) {
          return mergeFrom((RpcManualMessage.ManualMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcManualMessage.ManualMessage other) {
        if (other == RpcManualMessage.ManualMessage.getDefaultInstance()) return this;
        if (!other.getHistoryId().isEmpty()) {
          historyId_ = other.historyId_;
          onChanged();
        }
        if (other.getExitCode() != 0) {
          setExitCode(other.getExitCode());
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
        RpcManualMessage.ManualMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (RpcManualMessage.ManualMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object historyId_ = "";
      /**
       * <code>string historyId = 1;</code>
       */
      public java.lang.String getHistoryId() {
        java.lang.Object ref = historyId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          historyId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string historyId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getHistoryIdBytes() {
        java.lang.Object ref = historyId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          historyId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string historyId = 1;</code>
       */
      public Builder setHistoryId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        historyId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string historyId = 1;</code>
       */
      public Builder clearHistoryId() {
        
        historyId_ = getDefaultInstance().getHistoryId();
        onChanged();
        return this;
      }
      /**
       * <code>string historyId = 1;</code>
       */
      public Builder setHistoryIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        historyId_ = value;
        onChanged();
        return this;
      }

      private int exitCode_ ;
      /**
       * <code>sint32 exitCode = 2;</code>
       */
      public int getExitCode() {
        return exitCode_;
      }
      /**
       * <code>sint32 exitCode = 2;</code>
       */
      public Builder setExitCode(int value) {
        
        exitCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>sint32 exitCode = 2;</code>
       */
      public Builder clearExitCode() {
        
        exitCode_ = 0;
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


      // @@protoc_insertion_point(builder_scope:ManualMessage)
    }

    // @@protoc_insertion_point(class_scope:ManualMessage)
    private static final RpcManualMessage.ManualMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RpcManualMessage.ManualMessage();
    }

    public static RpcManualMessage.ManualMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ManualMessage>
        PARSER = new com.google.protobuf.AbstractParser<ManualMessage>() {
      @java.lang.Override
      public ManualMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ManualMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ManualMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ManualMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public RpcManualMessage.ManualMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ManualMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ManualMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024manual_message.proto\"4\n\rManualMessage\022" +
      "\021\n\thistoryId\030\001 \001(\t\022\020\n\010exitCode\030\002 \001(\021B(\n\022" +
      "com.dfire.protocolB\020RpcManualMessageH\001b\006" +
      "proto3"
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
        }, assigner);
    internal_static_ManualMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ManualMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ManualMessage_descriptor,
        new java.lang.String[] { "HistoryId", "ExitCode", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
