<template>
  <div class="container mt-4">
    <h2>Seznam rezervacij</h2>
    <table class="table">
      <thead>
        <tr>
          <th>Začetek</th>
          <th>Konec</th>
          <th>Motor (model)</th>
          <th>Uporabnik (ime in priimek)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="r in rezervacije" :key="r.id">
          <td>{{ r.datumZacetka }}</td>
          <td>{{ r.datumKonca }}</td>
          <td>{{ r.motor?.model }}</td>
          <td>{{ r.uporabnik?.ime }} {{ r.uporabnik?.priimek }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rezervacije: [],
    };
  },
  mounted() {
    fetch("http://localhost:8080/rezervacije")
      .then((res) => res.json())
      .then((data) => {
        this.rezervacije = data;
      });
  },
};
</script>
