<template>
  <div class="d-flex align-center flex-column">
    <v-card width="400">
      <v-card-header>
        <v-card-header-text>
          <v-card-title>Login</v-card-title>
        </v-card-header-text>
      </v-card-header>

      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="username" label="Username" :rules="nameRules" required></v-text-field>

          <v-text-field v-model="password" :append-icon="show1 ? mdiEyeOff : mdiEye"
                        :type="show1 ? 'text' : 'password'" name="input-10-1" label="Normal with hint text"
                        @click:append="show1 = !show1">
          </v-text-field>


          <v-btn color="success" class="mr-4" @click="login">
            Login
          </v-btn>
        </v-form>

      </v-card-text>
    </v-card>
  </div>
</template>

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
      var response = await $fetch('http://127.0.0.1:9050/graphql',
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
      //   const router = useRouter();
      //  await router.push('/bus')
    }
  },

}

</script>
