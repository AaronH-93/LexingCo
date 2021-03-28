package grpc.services.LexingCoOrdering

import grpc.services.LexingCoOrdering.LexingCoOrderingServiceGrpc.LexingCoOrderingServiceImplBase
import io.grpc.Context
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

    //orderStockServerStream takes in the single message from the Warehouse client and
    //returns a message containing the part and quantity that have been ordered
    override fun orderStockServerStream(request: WarehouseRestockRequest, responseObserver: StreamObserver<OrderRestockReply>) {
        println("Receiving warehouse request for new stock")
        val quantity = "5";
        var forked = Context.current().fork()
        var old = forked.attach()
        var preFilteredRequestList = request.text.split("\n").toTypedArray()
        var partsList = preFilteredRequestList.copyOf(preFilteredRequestList.size - 1)

        try {
            for (part in partsList) {
                println("Ordering $quantity new stock of $part")
                responseObserver.onNext(OrderRestockReply.newBuilder().setText("$part\n$quantity").build())
            }
        } finally{
            forked.detach(old)
        }
        responseObserver.onCompleted()
    }
}