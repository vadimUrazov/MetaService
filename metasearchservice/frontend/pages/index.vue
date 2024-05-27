<template>
  <NuxtLayout name="authenticated">


    <v-card width="800" height="400" class="td"  location="center" position="fixed">
      <div class="text-center">


     Metasearch platform that interacts with the search services of transport companies.<br>
      The platform gathers ranked search results for a specific query from several search engines of companies,
      using linking algorithms, builds possible routes taking into account the parameters of transport trips,
      and finds among the received routes the optimal one in terms of time and cost.<br>
      In addition, it is possible to purchase tickets for trips on the route selected by the user.<br>
      </div>

    </v-card>
      <div class="footer">
        <p>Author</p>
        <p>&copy Urazov Vadim, JAVA DEVELOPER</p>
      </div>

  </NuxtLayout>

</template>
<style>
.td {
  text-align: center;
  vertical-align: middle;
}
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
.text-center {
  margin: 0;
  padding:0;
  text-align: center;
  position: absolute;
  top: 50%;
  left:50%;
  transform: translateX(-50%) translateY(-50%);
}
</style>
<script>

</script>