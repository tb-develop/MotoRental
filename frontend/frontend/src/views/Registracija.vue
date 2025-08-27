<template>
  <div class="form-container">
    <h2>Dodaj uporabnika</h2>
    <form @submit.prevent="submitForm" novalidate>
      <div class="form-row">
        <label for="imePriimek">Ime in priimek:</label>
        <input id="imePriimek" v-model="form.imePriimek" type="text" />
        <span class="error" v-if="errors.imePriimek">{{ errors.imePriimek }}</span>
      </div>

      <div class="form-row">
        <label for="naslov">Naslov:</label>
        <input id="naslov" v-model="form.naslov" type="text" />
        <span class="error" v-if="errors.naslov">{{ errors.naslov }}</span>
      </div>

      <div class="form-row">
        <label for="datumRojstva">Datum rojstva (dd.mm.yyyy):</label>
        <input id="datumRojstva" v-model="form.datumRojstva" type="text" placeholder="dd.mm.yyyy" />
        <span class="error" v-if="errors.datumRojstva">{{ errors.datumRojstva }}</span>
      </div>

      <div class="form-row checkbox-row">
        <label for="vozniski">Vozniško dovoljenje:</label>
        <input id="vozniski" v-model="form.vozniski" type="checkbox" />
      </div>

      <button type="submit" :disabled="loading">{{ loading ? 'Pošiljam...' : 'Registriraj' }}</button>

      <p v-if="serverError" class="error">{{ serverError }}</p>
      <p v-if="successMessage" class="success">{{ successMessage }}</p>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        imePriimek: '',
        naslov: '',
        datumRojstva: '',
        vozniski: false,
      },
      errors: {},
      loading: false,
      serverError: '',
      successMessage: '',
    };
  },
  methods: {
    validateName(value) {
      if (!value) return "Ime in priimek je obvezno.";
      if (/\d/.test(value)) return "Ime in priimek ne sme vsebovati številk.";
      return null;
    },
    validateNaslov(value) {
      if (!value) return "Naslov je obvezen.";
      return null;
    },
    validateDatumRojstva(value) {
      if (!value) return "Datum rojstva je obvezen.";
      const regex = /^(\d{2})\.(\d{2})\.(\d{4})$/;
      const match = value.match(regex);
      if (!match) return "Datum rojstva mora biti v formatu dd.mm.yyyy";

      const day = parseInt(match[1], 10);
      const month = parseInt(match[2], 10) - 1;
      const year = parseInt(match[3], 10);

      const date = new Date(year, month, day);
      if (date.getDate() !== day || date.getMonth() !== month || date.getFullYear() !== year) {
        return "Datum rojstva ni veljaven.";
      }

      const today = new Date();
      let age = today.getFullYear() - year;
      const m = today.getMonth() - month;
      if (m < 0 || (m === 0 && today.getDate() < day)) {
        age--;
      }

      if (age < 24) return "Uporabnik mora biti star vsaj 24 let.";

      return null;
    },
    validateForm() {
      this.errors = {};
      this.errors.imePriimek = this.validateName(this.form.imePriimek);
      this.errors.naslov = this.validateNaslov(this.form.naslov);
      this.errors.datumRojstva = this.validateDatumRojstva(this.form.datumRojstva);

      Object.keys(this.errors).forEach(key => {
        if (this.errors[key] === null) {
          delete this.errors[key];
        }
      });

      return Object.keys(this.errors).length === 0;
    },
    formatDateToISO(ddmmyyyy) {
      const parts = ddmmyyyy.split('.');
      if (parts.length !== 3) return null;
      return `${parts[2]}-${parts[1].padStart(2, '0')}-${parts[0].padStart(2, '0')}`;
    },
    async submitForm() {
      this.serverError = '';
      this.successMessage = '';
      if (!this.validateForm()) return;

      this.loading = true;

      const payload = {
        imePriimek: this.form.imePriimek.trim(),
        naslov: this.form.naslov.trim(),
        datumRojstva: this.formatDateToISO(this.form.datumRojstva),
        vozniski: this.form.vozniski,
      };

      try {
        const response = await fetch('http://localhost:8080/uporabniki', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload),
        });

        if (!response.ok) {
          const errorText = await response.text();
          this.serverError = errorText || "Napaka pri pošiljanju podatkov.";
        } else {
          this.successMessage = "Uporabnik je uspešno dodan.";
          this.form.imePriimek = '';
          this.form.naslov = '';
          this.form.datumRojstva = '';
          this.form.vozniski = false;
        }
      } catch {
        this.serverError = "Napaka pri povezavi s strežnikom.";
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
.form-container {
  max-width: 600px;
  margin: 20px auto;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 4px;
  font-family: Arial, sans-serif;
}

.form-row {
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}

.form-row label {
  flex: 0 0 150px;
  text-align: right;
  margin-right: 10px;
}

.form-row input[type="text"] {
  flex: 1;
  padding: 6px 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.checkbox-row label {
  flex: 0 0 auto;
  margin-right: 10px;
  text-align: left;
}

.error {
  color: red;
  font-size: 0.85em;
  margin-left: 10px;
}

.success {
  color: green;
  margin-top: 15px;
}

button {
  padding: 10px 20px;
  background-color: #007BFF;
  border: none;
  color: white;
  font-weight: bold;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

button:hover:not(:disabled) {
  background-color: #0056b3;
}
</style>