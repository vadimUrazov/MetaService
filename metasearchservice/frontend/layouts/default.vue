<template>
  <v-app :theme="theme">
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
  computed: {
    theme() {
      return this.model > 0 ? 'dark' : 'light'
    }
  },
}
</script>
