<template>
  <v-app :theme="theme">
    <v-app-bar app>
      <v-btn flat nuxt to="/"> Home</v-btn>
      <v-btn flat nuxt to="/graphPath"> Paths</v-btn>
      <v-switch v-model="model" hide-details inset></v-switch>
    </v-app-bar>
    <v-main>
      <slot/>
    </v-main>
  </v-app>
</template>

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
      'paths',
      'graph'
    ],
  }),
  computed: {
    theme() {
      return this.model > 0 ? 'dark' : 'light'
    }
  },
}
</script>
