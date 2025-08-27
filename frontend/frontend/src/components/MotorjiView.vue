<template>
  <div class="container mt-4">
    <h2>Seznam motorjev</h2>
    <table class="table">
      <thead>
        <tr>
          <th>Model</th>
          <th>Barva</th>
          <th>Letnik</th>
          <th>Cena najema</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="motor in motorji" :key="motor.id">
          <td>{{ motor.model }}</td>
          <td>{{ motor.barva }}</td>
          <td>{{ motor.letnik }}</td>
          <td>{{ motor.cenaNajema }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      motorji: [],
    };
  },
  mounted() {
    fetch("http://localhost:8080/motorji")
      .then((res) => res.json())
      .then((data) => {
        this.motorji = data;
      });
  },
};
</script>
