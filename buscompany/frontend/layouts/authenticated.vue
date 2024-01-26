<template>
  <v-app :theme="theme">
    <v-app-bar app>
      <v-btn flat nuxt to="/"> Home</v-btn>
      <v-btn flat nuxt to="/users"> Users</v-btn>
      <v-btn flat nuxt to="/graph"> Graph</v-btn>
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
