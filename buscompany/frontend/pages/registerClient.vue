<template>
  <v-form v-model="valid">
    <v-container>
      <v-row>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="firstname"
              :counter="50"
              :rules="nameRules"
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
              :counter="50"
              :rules="nameRules"
              label="Last name"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="middlename"
              label="Middle name"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="login"
              :rules="authRules"
              label="Login"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="password"
              :rules="authRules"
              label="Password"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
          ></v-text-field>
        </v-col>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="phone"
              label="Phone"
              required
          ></v-text-field>
        </v-col>
        <v-btn
            :disabled="dialog"
            :loading="dialog"
            class="white--text"
            color="purple darken-2"
            @click="registerCl"
        >
          Sign Up
        </v-btn>

      </v-row>
    </v-container>
  </v-form>
</template>

<script>

export default {
  data: () => ({
    valid: false,
    firstname: '',
    lastname: '',
    middlename: '',
    login: '',
    password: '',
    email: '',
    phone: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 50 || 'Name must be less than 10 characters',
    ],
    authRules: [
      v => !!v || 'Field is required',
      v => v.length > 8 && v.length <= 50 || 'Name must be less than 50 characters and more 8',
    ],
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid',
    ],

  }),
  methods: {
    async registerCl() {
      var response = await $fetch('http://127.0.0.1:9090/graphql',
          {
            method: 'POST',
            body: {
              "query": "mutation{  " +
                  "registerAdmin(\n" +
                  "    request: {" +
                  "surname: " + this.firstname + "\",\n" +
                  "name: " + this.lastname + "\",\n" +
                  " middlename: " + this.middlename + "\",\n" +
                  "login: " + this.login + "\",\n" +
                  "      password: " + this.password + "\",\n" +
                  "    email:" + this.email + "\",\n" +
                  "  phone:" + this.phone +
                  "}\n" +
                  "  ) {\n" +
                  "    id\n" +
                  "    surname\n" +
                  "    name\n" +
                  "  }"
            },
          })
      alert("Client id" + response.data.registerClient.id)
      if (response.data.registerClient.id > 0) {
        this.$router.push('/login');
      }
    }
  }
}
</script>

<style scoped>

</style>