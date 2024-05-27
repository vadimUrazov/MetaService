<template>
  <v-app :theme="theme">
    <v-app-bar app>
      <v-btn flat nuxt to="/"> Home</v-btn>
      <v-btn flat nuxt to="/paths"> Routes</v-btn>
    </v-app-bar>

    <v-main class="img">
      <v-container >
      <slot/>
      </v-container>
    </v-main>
  </v-app>

</template>
<style>
.img{
  background-image: url('../images/img.png');
  height: 100%;
  width: 100%;
  background-size: cover;
}
</style>
<script setup>
const model = useState('model', () => 0)
</script>
<script>
export default {
  data: () => ({
    cards: ['Today', 'Yesterday'],
    drawer: null,
    links: [
      '/',
      'paths'
    ],
  }),
  computed: {
    theme() {
      return this.model > 0 ? 'dark' : 'light'
    }
  },
}
</script>
