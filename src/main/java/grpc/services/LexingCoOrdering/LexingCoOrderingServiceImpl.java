// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LexingCoOrdering.proto

package grpc.services.LexingCoOrdering;

public final class LexingCoOrderingServiceImpl {
  private LexingCoOrderingServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LexingCo_MessageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LexingCo_MessageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LexingCo_MessageReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LexingCo_MessageReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026LexingCoOrdering.proto\022\010LexingCo\"\036\n\016Me" +
      "ssageRequest\022\014\n\004text\030\001 \001(\t\"\034\n\014MessageRep" +
      "ly\022\014\n\004text\030\002 \001(\t2\\\n\027LexingCoOrderingServ" +
      "ice\022A\n\013sendMessage\022\030.LexingCo.MessageReq" +
      "uest\032\026.LexingCo.MessageReply\"\000B?\n\036grpc.s" +
      "ervices.LexingCoOrderingB\033LexingCoOrderi" +
      "ngServiceImplP\001b\006proto3"
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
    internal_static_LexingCo_MessageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LexingCo_MessageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LexingCo_MessageRequest_descriptor,
        new java.lang.String[] { "Text", });
    internal_static_LexingCo_MessageReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_LexingCo_MessageReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LexingCo_MessageReply_descriptor,
        new java.lang.String[] { "Text", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
