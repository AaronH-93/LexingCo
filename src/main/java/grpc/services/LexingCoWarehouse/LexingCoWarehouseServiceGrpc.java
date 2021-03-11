package grpc.services.LexingCoWarehouse;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: LexingCoWarehouse.proto")
public final class LexingCoWarehouseServiceGrpc {

  private LexingCoWarehouseServiceGrpc() {}

  public static final String SERVICE_NAME = "LexingCo.LexingCoWarehouseService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.LexingCoWarehouse.MessageRequest,
      grpc.services.LexingCoWarehouse.MessageReply> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = grpc.services.LexingCoWarehouse.MessageRequest.class,
      responseType = grpc.services.LexingCoWarehouse.MessageReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.LexingCoWarehouse.MessageRequest,
      grpc.services.LexingCoWarehouse.MessageReply> getSendMessageMethod() {
    io.grpc.MethodDescriptor<grpc.services.LexingCoWarehouse.MessageRequest, grpc.services.LexingCoWarehouse.MessageReply> getSendMessageMethod;
    if ((getSendMessageMethod = LexingCoWarehouseServiceGrpc.getSendMessageMethod) == null) {
      synchronized (LexingCoWarehouseServiceGrpc.class) {
        if ((getSendMessageMethod = LexingCoWarehouseServiceGrpc.getSendMessageMethod) == null) {
          LexingCoWarehouseServiceGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<grpc.services.LexingCoWarehouse.MessageRequest, grpc.services.LexingCoWarehouse.MessageReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "LexingCo.LexingCoWarehouseService", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoWarehouse.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoWarehouse.MessageReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LexingCoWarehouseServiceMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LexingCoWarehouseServiceStub newStub(io.grpc.Channel channel) {
    return new LexingCoWarehouseServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LexingCoWarehouseServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LexingCoWarehouseServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LexingCoWarehouseServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LexingCoWarehouseServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LexingCoWarehouseServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(grpc.services.LexingCoWarehouse.MessageRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoWarehouse.MessageReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.LexingCoWarehouse.MessageRequest,
                grpc.services.LexingCoWarehouse.MessageReply>(
                  this, METHODID_SEND_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class LexingCoWarehouseServiceStub extends io.grpc.stub.AbstractStub<LexingCoWarehouseServiceStub> {
    private LexingCoWarehouseServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoWarehouseServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoWarehouseServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoWarehouseServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(grpc.services.LexingCoWarehouse.MessageRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoWarehouse.MessageReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LexingCoWarehouseServiceBlockingStub extends io.grpc.stub.AbstractStub<LexingCoWarehouseServiceBlockingStub> {
    private LexingCoWarehouseServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoWarehouseServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoWarehouseServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoWarehouseServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.LexingCoWarehouse.MessageReply sendMessage(grpc.services.LexingCoWarehouse.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LexingCoWarehouseServiceFutureStub extends io.grpc.stub.AbstractStub<LexingCoWarehouseServiceFutureStub> {
    private LexingCoWarehouseServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoWarehouseServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoWarehouseServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoWarehouseServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.LexingCoWarehouse.MessageReply> sendMessage(
        grpc.services.LexingCoWarehouse.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LexingCoWarehouseServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LexingCoWarehouseServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((grpc.services.LexingCoWarehouse.MessageRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.LexingCoWarehouse.MessageReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LexingCoWarehouseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LexingCoWarehouseServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.LexingCoWarehouse.LexingCoWarehouseServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LexingCoWarehouseService");
    }
  }

  private static final class LexingCoWarehouseServiceFileDescriptorSupplier
      extends LexingCoWarehouseServiceBaseDescriptorSupplier {
    LexingCoWarehouseServiceFileDescriptorSupplier() {}
  }

  private static final class LexingCoWarehouseServiceMethodDescriptorSupplier
      extends LexingCoWarehouseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LexingCoWarehouseServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LexingCoWarehouseServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LexingCoWarehouseServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}