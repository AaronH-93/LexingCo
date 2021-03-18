package grpc.services.LexingCoOrdering

import grpc.services.LexingCoOrdering.LexingCoOrderingServiceGrpc.LexingCoOrderingServiceImplBase
import io.grpc.Server
import javax.jmdns.JmDNS
import java.net.InetAddress
import javax.jmdns.ServiceInfo
import java.io.IOException
import io.grpc.stub.StreamObserver
import kotlin.jvm.JvmStatic
import io.grpc.ServerBuilder
import java.lang.InterruptedException

class OrderingServer : LexingCoOrderingServiceImplBase() {
    private fun registerService() {
        val serviceType = "_ordering._tcp.local."
        val serviceName = "LexingCoOrdering"
        val serviceDescription = "Ordering Server will order parts"
        try {
            val serviceInfo = ServiceInfo.create(serviceType, serviceName, port, serviceDescription)

            //Create JmDNS instance
            val jmDNS: JmDNS? = JmDNS.create(InetAddress.getLocalHost())
            // Register factory service
            jmDNS?.registerService(serviceInfo)

            System.out.printf("Registered %s in port %d \n", serviceType, port)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun sendMessage(request: MessageRequest, responseObserver: StreamObserver<MessageReply>) {
        println("Receiving Order request")
        responseObserver.onNext(MessageReply.newBuilder().setText("200 - OK from ordering service").build())
        responseObserver.onCompleted()
    }

    companion object {
        private const val port = 50052
        @JvmStatic
        fun main(args: Array<String>) {
            val orderingServer = OrderingServer()
            orderingServer.registerService()
            val server: Server
            try {
                server = ServerBuilder.forPort(port).addService(orderingServer).build().start()
                println("Ordering Server started, listening on $port")
                server.awaitTermination()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}