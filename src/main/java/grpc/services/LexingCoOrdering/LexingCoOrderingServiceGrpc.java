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
  private static volatile io.grpc.MethodDescriptor<grpc.services.LexingCoOrdering.StockRequest,
      grpc.services.LexingCoOrdering.StockReply> getOrderStockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "orderStock",
      requestType = grpc.services.LexingCoOrdering.StockRequest.class,
      responseType = grpc.services.LexingCoOrdering.StockReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.LexingCoOrdering.StockRequest,
      grpc.services.LexingCoOrdering.StockReply> getOrderStockMethod() {
    io.grpc.MethodDescriptor<grpc.services.LexingCoOrdering.StockRequest, grpc.services.LexingCoOrdering.StockReply> getOrderStockMethod;
    if ((getOrderStockMethod = LexingCoOrderingServiceGrpc.getOrderStockMethod) == null) {
      synchronized (LexingCoOrderingServiceGrpc.class) {
        if ((getOrderStockMethod = LexingCoOrderingServiceGrpc.getOrderStockMethod) == null) {
          LexingCoOrderingServiceGrpc.getOrderStockMethod = getOrderStockMethod = 
              io.grpc.MethodDescriptor.<grpc.services.LexingCoOrdering.StockRequest, grpc.services.LexingCoOrdering.StockReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "LexingCo.LexingCoOrderingService", "orderStock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoOrdering.StockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoOrdering.StockReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LexingCoOrderingServiceMethodDescriptorSupplier("orderStock"))
                  .build();
          }
        }
     }
     return getOrderStockMethod;
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
    public void orderStock(grpc.services.LexingCoOrdering.StockRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoOrdering.StockReply> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderStockMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrderStockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.LexingCoOrdering.StockRequest,
                grpc.services.LexingCoOrdering.StockReply>(
                  this, METHODID_ORDER_STOCK)))
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
    public void orderStock(grpc.services.LexingCoOrdering.StockRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoOrdering.StockReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderStockMethod(), getCallOptions()), request, responseObserver);
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
    public grpc.services.LexingCoOrdering.StockReply orderStock(grpc.services.LexingCoOrdering.StockRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderStockMethod(), getCallOptions(), request);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.LexingCoOrdering.StockReply> orderStock(
        grpc.services.LexingCoOrdering.StockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrderStockMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ORDER_STOCK = 0;

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
        case METHODID_ORDER_STOCK:
          serviceImpl.orderStock((grpc.services.LexingCoOrdering.StockRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.LexingCoOrdering.StockReply>) responseObserver);
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
              .addMethod(getOrderStockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
