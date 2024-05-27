<template>
  <NuxtLayout name="authenticated">
    <div class="d-flex align-center flex-column">
    <v-card width="400"  location="center" position="fixed">
      <v-card-header>
        <v-card-header-text>
          <v-card-title align="middle">
           <b> Login </b>
          </v-card-title>
        </v-card-header-text>
      </v-card-header>

      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="username" :rules="nameRules" label="Username"
                        required></v-text-field>

          <v-text-field v-model="password" :rules="passRules" :append-icon="show1 ? mdiEyeOff : mdiEye"
                        :type="show1 ? 'text' : 'password'"  label="Password"
                        name="input-10-1"
                        @click:append="show1 = !show1" required>
          </v-text-field>


          <v-btn class="mr-4 bt" color="success" @click="login" location="center" >
            Sign in
          </v-btn>
          <font size="5" >

         <a class="hyp lin"  href="/registerClient" >Sign up</a>

          </font>
        </v-form>
      </v-card-text>
    </v-card>
  </div>

  <div class="footer">
  <section>
    <p>Author</p>
    <p>&copy Urazov Vadim, JAVA DEVELOPER</p>
  </section>
  </div>
    </NuxtLayout>
</template>
<style>
form {

}
.lin{
  margin-bottom: 300px;
}
.hyp{
  margin-left: 170px;
}
.bt{
  margin-top: 10px;
}
.img{
  background-image: url('../images/img.png');
  height: 100%;
  width: 100%;
  background-size: cover;
}
.footer {
  background-color: #6fc095;
  position: fixed;
  right: 0;
  bottom: 0;
  text-align: center;
  width: 100%;
  font-size: 10px;
  font-weight: bold;
  color: #fafaff;
}
</style>
<script>
import {mdiEye, mdiEyeOff} from '@mdi/js'

export default {
  data: () => ({
    mdiEye,
    mdiEyeOff,
    valid: false,
    username: "",
    show1: false,
    show2: true,
    show3: false,
    show4: false,
    password: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 50 || 'Name must be less than 50 characters',
    ],
    passRules: [
      v => !!v || 'Name is required',
      v => v.length >= 8 || 'Min 8 characters'
    ],

    rules: {
      required: value => !!value || 'Required.',
      min: v => v.length >= 8 || 'Min 8 characters',
      emailMatch: () => (`The email and password you entered don't match`),
    },
  }),
  methods: {
    validate() {
      this.$refs.form.validate()
    },
    reset() {
      this.$refs.form.reset()
    },
    resetValidation() {
      this.$refs.form.resetValidation()
    },

    async login() {
      var response = await $fetch('http://127.0.0.1:9080/graphql',
          {
            method: 'POST',
            body: {
              "query": "mutation{  " +
                  "login(loginDto:{\n" +
                  "      login: \"" + this.username + "\",\n" +
                  "      password: \"" + this.password + "\"\n" +
                  "    }){\n" +
                  "       id\n" +
                  "       surname\n" +
                  "       name\n" +
                  "       jwtToken\n" +
                  "      \n" +
                  "    }}", "variables": null
            },

          })
      console.log(response.data.login.jwtToken)
      localStorage.setItem('Authorization', "Bearer " + response.data.login.jwtToken)
if(!response.data.login.jwtToken.equals('')){
  window.location='http://localhost:63342/untitled/metaservice/pages/Order.html?_ijt=dot915jpivb9l69aqsk1b3vjqg';
}
    }
  }

}

</script>
