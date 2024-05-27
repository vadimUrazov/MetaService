<template>
  <NuxtLayout name="authenticated">
  <v-form v-model="valid">
    <p align="middle">
      <font size="5" color="#23218b" face="Arial">
       <b>Register Client</b>
      </font>
      </p>
    <v-container>
      <v-card class="card">
      <v-row>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              v-model="firstname"
              :counter="50"
              :rules="nameRules"
              label="Surname"
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
              v-model="lastname"
              :counter="50"
              :rules="nameRules"
              label="Name"
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
              v-model="phone"
              :rules="phoneRules"
              label="Phone"
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


      </v-row>
      <v-btn
          :disabled="dialog"
          :loading="dialog"
          class="white--text"
          color="purple darken-2"
      @click="registerCl"
     location="center" >
        Sign Up
      </v-btn>
        </v-card>
    </v-container>
  </v-form>

    <div class="footer">
      <p>Author</p>
      <p>&copy Urazov Vadim, JAVA DEVELOPER</p>
    </div>
    </NuxtLayout>
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

    ],
    authRules: [
      v => !!v || 'Field is required',
      v => v.length <= 50 || 'Name must be less than 10 characters',
    ],
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid',
    ],
    phoneRules: [
      v => !!v || 'Phone is required',
      v=>/^(\+)?((\d{2,3}) ?\d|\d)(([ -]?\d)|( ?(\d{2,3}) ?)){5,12}\d$/.test(v) || 'Incorrect phone',
    ],

  }),
  methods: {
    async registerCl() {
      var response = await $fetch('http://127.0.0.1:9080/graphql',
          {
            method: 'POST',
            body: {
              "query": "mutation{  " +
                  "registerClient(\n" +
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
                  "    middlename\n" +
                  "}}", "variables": null
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
.footer {
  background-color: #9aa9e3;
  position: fixed;
  right: 0;
  bottom: 0;
  text-align: center;
  width: 99%;
  font-size: 10px;
  font-weight: bold;
  color: #fafaff;
}
.card{
  padding: 2.5%;
}
</style>