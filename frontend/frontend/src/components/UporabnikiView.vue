<template>
  <div class="container mt-4">
    <h2>Seznam uporabnikov</h2>
    <table class="table">
      <thead>
        <tr>
          <th>Ime</th>
          <th>Priimek</th>
          <th>Naslov</th>
          <th>Starost</th>
          <th>Vozniško dovoljenje</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="u in uporabniki" :key="u.id">
          <td>{{ u.ime }}</td>
          <td>{{ u.priimek }}</td>
          <td>{{ u.naslov }}</td>
          <td>{{ u.starost }}</td>
          <td>{{ u.vozniskiIzpit ? "DA" : "NE" }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      uporabniki: [],
    };
  },
  mounted() {
    fetch("http://localhost:8080/uporabniki")
      .then((res) => res.json())
      .then((data) => {
        this.uporabniki = data;
      });
  },
};
</script>
