package homemarket.Exceptions.Handlers

import homemarket.Exceptions.MailEnUsoException
import homemarket.Exceptions.ProductoNoEncotradoException
import homemarket.Exceptions.UsuarioNoExistenteException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class UsuarioControllerAdvice: ResponseEntityExceptionHandler() {

            @ExceptionHandler(value = [(UsuarioNoExistenteException::class)])
            fun handlerUsuarioNoExistente(exception: UsuarioNoExistenteException,
                                          request: WebRequest): ResponseEntity<ErrorDetails>{
                val errorDetails = ErrorDetails("No se encontro ningún usuario con el mail/nombre de usuario que ingreso",
                                                exception.message!!)

                return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
            }

            @ExceptionHandler(value = [(MailEnUsoException::class)])
            fun handleMailEnUso(exception: MailEnUsoException,
                                request: WebRequest): ResponseEntity<ErrorDetails>{
                val errorDetails = ErrorDetails("El mail que puso ya esta en uso",
                                                exception.message!!)

                return ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE)
            }

}