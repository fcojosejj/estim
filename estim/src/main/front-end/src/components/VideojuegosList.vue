<template>
  <h1>Listado de videojuegos</h1>
  <table id="idVideojuegos" class="display" style="width:100%">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Desarrolladora</th>
      <th>Descripción</th>
      <th>Precio</th>
      <th>Valoracion</th>
      <th>Opciones</th>
    </tr>
    </thead>
    <tbody v-for="v in datos" :key="v.id">
    <tr>
      <td>{{v.id}}</td>
      <td>{{v.nombre}}</td>
      <td>{{v.desarrolladora}}</td>
      <td>{{v.descripcion}}</td>
      <td>{{v.precio}}€</td>
      <td>{{v.valoracion}}</td>
      <td>
        <button class="btn btn-warning" @click.prevent="$emit('selectVideojuego',v.id)">
          Editar</button>
        <button class="btn btn-danger" style="margin-left: 5px" @click.prevent="$emit('deleteVideojuego',v.id)">
          Borra</button>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "VideojuegosList",
  props: [
    'datos', //Component attribute (single binding)
  ],
  emits: ['selectVideojuego','deleteVideojuego'],
  data() {
    return {
      order: 'id'
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
</style>