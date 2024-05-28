<template>
  <v-app :theme="theme">
    <v-app-bar app>
      <v-btn flat to="/" nuxt>
      <font size="3"  >
       Home
       </font>
       </v-btn>
    
      <v-btn flat to="/login" nuxt> 
      <font size="3"  >
      Login
      </font>
      </v-btn>
      <v-switch hide-details inset v-model="model"></v-switch>
    </v-app-bar>
    <v-main class="img">
      <v-container>
      <slot/>
       </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
const model = useState('model', () => 0)
</script>
<style>
.img{
  background-image: url('../images/img.png');
  height: 100%;
  width: 100%;
  background-size: cover;
}
</style>
<script>

export default {
  data: () => ({
    cards: ['Today', 'Yesterday'],
    drawer: null,
    links: [
      '/',
      'users',
      'graph'
    ],
  }),
  computed: {
    theme() {
      return this.model > 0 ? 'dark' : 'light'
    }
  },
  // setup() {
  //   const theme = ref('light')

  //   return {
  //     theme,
  //     toggleTheme: () => theme.value = theme.value === 'light' ? 'dark' : 'light'
  //   }
  // }
}
</script>
