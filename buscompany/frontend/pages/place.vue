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
              v-model="Order_id"
              label="Order id"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="firstname"
              :rules="nameRules"
              :counter="50"
              label="First name"
              required
          ></v-text-field>
        </v-col>

        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="lastname"
              :rules="nameRules"
              :counter="50"
              label="Last name"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="Passport"
              label="passport"
              required
          ></v-text-field>
        </v-col>

        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="Place"
              label="Place"
              required
          ></v-text-field>
        </v-col>

        <v-btn
            :disabled="dialog"
            :loading="dialog"
            class="white--text"
            color="purple darken-2"
        >
          Choose
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
    Order_id: '',
    firstname: '',
    lastname: '',
    passport: '',
    place: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 50 || 'Name must be less than 50 characters',
    ],
  }),
}
</script>

<style scoped>

</style>