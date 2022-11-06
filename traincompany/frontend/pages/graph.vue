<template>
  <NuxtLayout name="authenticated">
    <h2>Starlink Launches</h2>
    <p>{{ users }}</p>
  </NuxtLayout>
</template>

<script lang="ts" setup>
const router = useRouter();
const users = await $fetch('http://127.0.0.1:8090/graphql',
    {
      method: 'POST',
      body: {"query": "query healthCheck {healthCheck}", "variables": null, "operationName": "healthCheck"},
      headers: {
        Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhIiwiaWF0IjoxNjUxNDE0MjE2LCJleHAiOjE2NTE1MDA2MTZ9.D6LiecwV7mP5aCkS2J3qoPTAjZnDpH_yEB7IoH2Jwhbg3M88llMTShEkMh9iZgCWIdw1aCrElWlS_hZ2HCRMyQ',
        'Cache-Control': 'no-cache'
      }
    })
if (users.errors) {
  if (JSON.stringify(users.errors).includes("UNAUTHORIZED")) {
    await router.push('/login')
    // todo redirect to login page
    console.log("UNAUTHORIZED")
    // FORBIDDEN
  }
}
console.log(users)
</script>
