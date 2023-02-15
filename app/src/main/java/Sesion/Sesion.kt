package Sesion

import java.time.LocalDateTime

class Sesion {
    val hora : LocalDateTime

    constructor() {
        hora = LocalDateTime.now()
    }

    constructor(hora : LocalDateTime) {
        this.hora = hora
    }
}