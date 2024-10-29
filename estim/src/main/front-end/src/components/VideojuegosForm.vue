<template>
  <h2 v-if="videojuego.id>0">Edición de videojuego </h2>
  <h2 v-if="videojuego.id===0">Alta de videojuego </h2>


  <form novalidate name='frmVideojuegos' class="formulario">

    <h3>ID: <span>{{ videojuego.id }}</span></h3>

    <div>
      <label>Nombre: </label><input name='nombre'
                                    v-model="videojuego.nombre" required class="form-control"
                                    :class="{'is-invalid': errorMsgs.nombre}"/>
      <label><span class="error">{{ errorMsgs.nombre }}</span></label>
    </div>

    <div>
      <label>Desarrolladora: </label><input name="desarrolladora"
                                 v-model="videojuego.desarrolladora" class="form-control"
                                 :class="{ 'is-invalid': errorMsgs.desarrolladora}"/>
      <label><span class="error">{{ errorMsgs.desarrolladora }}</span></label>
    </div>

    <div>
      <label>Descripcion: </label><input name='descripcion'
                                    v-model="videojuego.descripcion" required class="form-control"
                                    :class="{'is-invalid': errorMsgs.descripcion}"/>
      <label><span class="error">{{ errorMsgs.descripcion }}</span></label>
    </div>

    <div>
      <label>Precio: </label><input name='precio'
                                         v-model="videojuego.precio" required class="form-control"
                                         :class="{'is-invalid': errorMsgs.precio}"/>
      <label><span class="error">{{ errorMsgs.precio }}</span></label>
    </div>

    <div>
      <label>Valoracion: </label><input name='valoracion'
                                    v-model="videojuego.valoracion" required class="form-control"
                                    :class="{'is-invalid': errorMsgs.valoracion}"/>
      <label><span class="error">{{ errorMsgs.valoracion }}</span></label>
    </div>

    <br>

    <button class="btn btn-primary"
            @click.prevent="guardarVideojuego">Guardar
    </button>

  </form>
</template>

<script>

export default {
  props: [
    'videojuego' //Component attribute (single binding)
  ],
  inject: ['videojuegosDAO'],
  emits: [
    'updateVideojuego'
  ],
  data() {
    return {
      errorMsgs: {}
    }
  },
  computed: {
    hayErrores() {
      return Object.keys(this.errorMsgs).length > 0;
    }
  },
  methods: {
    async guardarVideojuego() {
      if (this.validarVideojuego()) {
        console.log("actualizando videojuego")
        try {
          if (this.videojuego.id === 0) {
            //create new client promise
            await this.videojuegosDAO.crea(this.videojuego)
          } else {
            //update videojuego promise
            await this.videojuegosDAO.guarda(this.videojuego)
          }
          this.$emit('updateVideojuego')
        } catch (err) {
          console.log("Errores de validación en servidor")
          err.forEach(error => {
            this.errorMsgs[error.name] = error.message;
          })
        }
      }
    },
    validarVideojuego() {
      console.log("Validación en videojuego")
      this.errorMsgs = {}; //clear previous errors
      let valido = true;

      if (this.videojuego.nombre.length < 1)
        this.errorMsgs.nombre = "La longitud del nombre no es correcta (min. 1 caracter)";
      if (this.videojuego.desarrolladora.length < 1)
        this.errorMsgs.desarrolladora = "La longitud de la desarrolladora no es correcta (min. 1 caracter)";
      if (this.videojuego.descripcion.length > 500)
        this.errorMsgs.descripcion = "La longitud de la descripción es demasiado larga (max. 500 caracteres)";
      if (this.videojuego.precio < 0)
        this.errorMsgs.precio = "El precio debe ser un valor positivo o 0";
      if (this.videojuego.valoracion < 0 || this.videojuego.valoracion > 10)
        this.errorMsgs.valoracion = "La valoracion debe ser un valor comprendido entre 0 y 10";

      if (Object.keys(this.errorMsgs).length > 0) {
        console.log("Errores de validación en videojuego")
        valido = false;
      }
      return valido;
    }
  },
}
</script>

<style scoped>

.formulario{
  margin: auto;
  width: 50%;
}

input{
  width: 50vw;
}

input[type=text] {
  width: 500px;
}

.error{
  color: red;
}
</style>