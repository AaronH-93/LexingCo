package grpc.services.LexingCoOrdering;

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
    comments = "Source: LexingCoOrdering.proto")
public final class LexingCoOrderingServiceGrpc {

  private LexingCoOrderingServiceGrpc() {}

  public static final String SERVICE_NAME = "LexingCo.LexingCoOrderingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.LexingCoOrdering.WarehouseRestockRequest,
      grpc.services.LexingCoOrdering.OrderRestockReply> getOrderStockServerStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "orderStockServerStream",
      requestType = grpc.services.LexingCoOrdering.WarehouseRestockRequest.class,
      responseType = grpc.services.LexingCoOrdering.OrderRestockReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.LexingCoOrdering.WarehouseRestockRequest,
      grpc.services.LexingCoOrdering.OrderRestockReply> getOrderStockServerStreamMethod() {
    io.grpc.MethodDescriptor<grpc.services.LexingCoOrdering.WarehouseRestockRequest, grpc.services.LexingCoOrdering.OrderRestockReply> getOrderStockServerStreamMethod;
    if ((getOrderStockServerStreamMethod = LexingCoOrderingServiceGrpc.getOrderStockServerStreamMethod) == null) {
      synchronized (LexingCoOrderingServiceGrpc.class) {
        if ((getOrderStockServerStreamMethod = LexingCoOrderingServiceGrpc.getOrderStockServerStreamMethod) == null) {
          LexingCoOrderingServiceGrpc.getOrderStockServerStreamMethod = getOrderStockServerStreamMethod = 
              io.grpc.MethodDescriptor.<grpc.services.LexingCoOrdering.WarehouseRestockRequest, grpc.services.LexingCoOrdering.OrderRestockReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "LexingCo.LexingCoOrderingService", "orderStockServerStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoOrdering.WarehouseRestockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoOrdering.OrderRestockReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LexingCoOrderingServiceMethodDescriptorSupplier("orderStockServerStream"))
                  .build();
          }
        }
     }
     return getOrderStockServerStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LexingCoOrderingServiceStub newStub(io.grpc.Channel channel) {
    return new LexingCoOrderingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LexingCoOrderingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LexingCoOrderingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LexingCoOrderingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LexingCoOrderingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LexingCoOrderingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void orderStockServerStream(grpc.services.LexingCoOrdering.WarehouseRestockRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoOrdering.OrderRestockReply> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderStockServerStreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrderStockServerStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.services.LexingCoOrdering.WarehouseRestockRequest,
                grpc.services.LexingCoOrdering.OrderRestockReply>(
                  this, METHODID_ORDER_STOCK_SERVER_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class LexingCoOrderingServiceStub extends io.grpc.stub.AbstractStub<LexingCoOrderingServiceStub> {
    private LexingCoOrderingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoOrderingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoOrderingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoOrderingServiceStub(channel, callOptions);
    }

    /**
     */
    public void orderStockServerStream(grpc.services.LexingCoOrdering.WarehouseRestockRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoOrdering.OrderRestockReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getOrderStockServerStreamMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LexingCoOrderingServiceBlockingStub extends io.grpc.stub.AbstractStub<LexingCoOrderingServiceBlockingStub> {
    private LexingCoOrderingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoOrderingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoOrderingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoOrderingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.services.LexingCoOrdering.OrderRestockReply> orderStockServerStream(
        grpc.services.LexingCoOrdering.WarehouseRestockRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getOrderStockServerStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LexingCoOrderingServiceFutureStub extends io.grpc.stub.AbstractStub<LexingCoOrderingServiceFutureStub> {
    private LexingCoOrderingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoOrderingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoOrderingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoOrderingServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ORDER_STOCK_SERVER_STREAM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LexingCoOrderingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LexingCoOrderingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ORDER_STOCK_SERVER_STREAM:
          serviceImpl.orderStockServerStream((grpc.services.LexingCoOrdering.WarehouseRestockRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.LexingCoOrdering.OrderRestockReply>) responseObserver);
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

  private static abstract class LexingCoOrderingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LexingCoOrderingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.LexingCoOrdering.LexingCoOrderingServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LexingCoOrderingService");
    }
  }

  private static final class LexingCoOrderingServiceFileDescriptorSupplier
      extends LexingCoOrderingServiceBaseDescriptorSupplier {
    LexingCoOrderingServiceFileDescriptorSupplier() {}
  }

  private static final class LexingCoOrderingServiceMethodDescriptorSupplier
      extends LexingCoOrderingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LexingCoOrderingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LexingCoOrderingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LexingCoOrderingServiceFileDescriptorSupplier())
              .addMethod(getOrderStockServerStreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
