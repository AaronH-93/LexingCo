package grpc.services.LexingCoFactory;

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
    comments = "Source: LexingCoFactory.proto")
public final class LexingCoFactoryServiceGrpc {

  private LexingCoFactoryServiceGrpc() {}

  public static final String SERVICE_NAME = "LexingCo.LexingCoFactoryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.LexingCoFactory.MessageRequest,
      grpc.services.LexingCoFactory.MessageReply> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = grpc.services.LexingCoFactory.MessageRequest.class,
      responseType = grpc.services.LexingCoFactory.MessageReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.LexingCoFactory.MessageRequest,
      grpc.services.LexingCoFactory.MessageReply> getSendMessageMethod() {
    io.grpc.MethodDescriptor<grpc.services.LexingCoFactory.MessageRequest, grpc.services.LexingCoFactory.MessageReply> getSendMessageMethod;
    if ((getSendMessageMethod = LexingCoFactoryServiceGrpc.getSendMessageMethod) == null) {
      synchronized (LexingCoFactoryServiceGrpc.class) {
        if ((getSendMessageMethod = LexingCoFactoryServiceGrpc.getSendMessageMethod) == null) {
          LexingCoFactoryServiceGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<grpc.services.LexingCoFactory.MessageRequest, grpc.services.LexingCoFactory.MessageReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "LexingCo.LexingCoFactoryService", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoFactory.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.LexingCoFactory.MessageReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LexingCoFactoryServiceMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LexingCoFactoryServiceStub newStub(io.grpc.Channel channel) {
    return new LexingCoFactoryServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LexingCoFactoryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LexingCoFactoryServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LexingCoFactoryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LexingCoFactoryServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LexingCoFactoryServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(grpc.services.LexingCoFactory.MessageRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoFactory.MessageReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.LexingCoFactory.MessageRequest,
                grpc.services.LexingCoFactory.MessageReply>(
                  this, METHODID_SEND_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class LexingCoFactoryServiceStub extends io.grpc.stub.AbstractStub<LexingCoFactoryServiceStub> {
    private LexingCoFactoryServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoFactoryServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoFactoryServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoFactoryServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(grpc.services.LexingCoFactory.MessageRequest request,
        io.grpc.stub.StreamObserver<grpc.services.LexingCoFactory.MessageReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LexingCoFactoryServiceBlockingStub extends io.grpc.stub.AbstractStub<LexingCoFactoryServiceBlockingStub> {
    private LexingCoFactoryServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoFactoryServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoFactoryServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoFactoryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.LexingCoFactory.MessageReply sendMessage(grpc.services.LexingCoFactory.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LexingCoFactoryServiceFutureStub extends io.grpc.stub.AbstractStub<LexingCoFactoryServiceFutureStub> {
    private LexingCoFactoryServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LexingCoFactoryServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LexingCoFactoryServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LexingCoFactoryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.LexingCoFactory.MessageReply> sendMessage(
        grpc.services.LexingCoFactory.MessageRequest request) {
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
    private final LexingCoFactoryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LexingCoFactoryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((grpc.services.LexingCoFactory.MessageRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.LexingCoFactory.MessageReply>) responseObserver);
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

  private static abstract class LexingCoFactoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LexingCoFactoryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.LexingCoFactory.LexingCoFactoryServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LexingCoFactoryService");
    }
  }

  private static final class LexingCoFactoryServiceFileDescriptorSupplier
      extends LexingCoFactoryServiceBaseDescriptorSupplier {
    LexingCoFactoryServiceFileDescriptorSupplier() {}
  }

  private static final class LexingCoFactoryServiceMethodDescriptorSupplier
      extends LexingCoFactoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LexingCoFactoryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LexingCoFactoryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LexingCoFactoryServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
