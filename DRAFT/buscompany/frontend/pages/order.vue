<template>
  <v-form v-model="valid">
    <v-container>
      <v-row>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="Client_id"
              label="Client id"
              required
          ></v-text-field>
        </v-col>

        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="Trip_id"
              label="Trip id"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="date"
              :rules="pasRules"
              label="yyyy-mm-dd"
              required
          ></v-text-field>
        </v-col>


        <v-btn
            :disabled="dialog"
            :loading="dialog"
            class="white--text"
            color="purple darken-2"
        >
          Create
        </v-btn>

      </v-row>
    </v-container>
  </v-form>
</template>

<script>
export default {
  data: () => ({
    valid: false,
    Client_id: '',
    Trip_id: '',
    date: '',
    pasRules: [
      v => !!v || 'Name is required',
      v => v.length > 0 || 'Name must be more than 0 characters',
    ],
  }),
}
</script>

<style scoped>

</style>