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
    internal_static_LexingCo_WarehouseRestockRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LexingCo_WarehouseRestockRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LexingCo_OrderRestockReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LexingCo_OrderRestockReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026LexingCoOrdering.proto\022\010LexingCo\"\'\n\027Wa" +
      "rehouseRestockRequest\022\014\n\004text\030\001 \001(\t\"!\n\021O" +
      "rderRestockReply\022\014\n\004text\030\002 \001(\t2w\n\027Lexing" +
      "CoOrderingService\022\\\n\026orderStockServerStr" +
      "eam\022!.LexingCo.WarehouseRestockRequest\032\033" +
      ".LexingCo.OrderRestockReply\"\0000\001B?\n\036grpc." +
      "services.LexingCoOrderingB\033LexingCoOrder" +
      "ingServiceImplP\001b\006proto3"
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
    internal_static_LexingCo_WarehouseRestockRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LexingCo_WarehouseRestockRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LexingCo_WarehouseRestockRequest_descriptor,
        new java.lang.String[] { "Text", });
    internal_static_LexingCo_OrderRestockReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_LexingCo_OrderRestockReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LexingCo_OrderRestockReply_descriptor,
        new java.lang.String[] { "Text", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
