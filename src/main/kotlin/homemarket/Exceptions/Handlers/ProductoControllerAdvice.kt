package homemarket.Exceptions.Handlers

import homemarket.Exceptions.ProductoNoEncotradoException
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
}