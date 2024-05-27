<template>
<NuxtLayout name="authenticated">
  <v-form v-model="valid">
  <p align="middle">
      <font size="5" color="#23218b" face="Arial">
       <b>Register Admin</b>
      </font>
      </p>
    <v-container>
      <v-row>
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
              label="Login"
              :rules="authRules"
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
              v-model="position"
              :rules="positionRules"
              label="Position"
              required
          ></v-text-field>
        </v-col>

        <v-btn
            :disabled="dialog"
            :loading="dialog"
            class="white--text"
            color="purple darken-2"
            @click="registerAdm">
          Sign Up
        </v-btn>

      </v-row>
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
    position: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 50 || 'Name must be less than 50 characters',
    ],
    authRules: [
      v => !!v || 'Field is required',
      v => v.length > 8 && v.length <= 50 || 'Field must be less than 50 characters and more 8',
    ],
    positionRules: [
      v => !!v || 'Position is required',
      v => v.length <= 50 || 'Position must be less than 50 characters',
    ],
  }),
  methods:{
    async registerAdm() {
      var response = await $fetch('http://127.0.0.1:8090/graphql',
          {
            method: 'POST',
            body: {
              "query": "mutation{  " +
                  "registerAdmin(\n" +
                  "    request: {" +
                  " surname: "+this.firstname + "\",\n" +
                      "name: "+this.lastname + "\",\n" +
                  " middlename: "+this.middlename + "\",\n" +
                  " login: "+this.login + "\",\n" +
                  "      password: "+this.password + "\",\n" +
                  " position:"+this.position +
                  "}\n" +
                  "  ) {\n" +
                  "    id\n" +
                  "    surname\n" +
                  "    name\n" +
                  "  }"
            },
          })
      if(response.data.registerAdmin.id>0){
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
</style>
