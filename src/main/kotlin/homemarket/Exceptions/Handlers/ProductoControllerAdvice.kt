package homemarket.Exceptions.Handlers

import homemarket.Exceptions.ProductoNoEncotradoException
import homemarket.Exceptions.StockInsuficienteParaProductoException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ProductoControllerAdvice: ResponseEntityExceptionHandler()  {

    @ExceptionHandler(value = [(ProductoNoEncotradoException::class)])
    fun handleProductoNoEncontrado(exception: ProductoNoEncotradoException,
                                   request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails("No se pudo encontrar ningun producto con el id que utilizaste",
                exception.message!!)

        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(value = [(StockInsuficienteParaProductoException::class)])
    fun handleStockInsuficiente(exception: StockInsuficienteParaProductoException,
                                   request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails("El producto que buscaste tiene menos stock del que indicaste",
                exception.message!!)

        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }
}