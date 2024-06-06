<template>
  <NuxtLayout name="authenticated">
    <v-form v-model="valid">
      <v-container>
        <v-card class="card">
        <v-row>
          <v-col cols="5"
                 md="2">
            <v-text-field v-model="from"
                          label="From station"
                          required>
            </v-text-field>
          </v-col>
          <v-col cols="5"
                 md="2">
            <v-text-field v-model="to"
                          label="To station"
                          required>
            </v-text-field>
          </v-col>
          <v-col cols="12"
                 md="2">
            <v-select
                :items="criteriaItems"
                v-model="criteria"
                label="Criteria"
            ></v-select>
          </v-col>
          <v-col
              cols="12"
              md="2">
            <v-select
                :items="items"
                v-model="transport"
                label="Transport"
            ></v-select>
          </v-col>
          <v-col
              cols="12"
              md="2">
            <v-text-field
                v-model="dateFrom"
                label="Date"
                required
            ></v-text-field>

          </v-col>
          <v-col
              cols="12"
              md="2">
            <v-btn class="mr-4" color="blue" @click="getP"  v-slot:default>
              Get
            </v-btn>
          </v-col>

          <v-data-table :items="tripRes">
          </v-data-table>

        </v-row>
        </v-card>
      </v-container>

      <v-btn  class="mr-4" color="blue" height="50px" width="150px"   location="center"
              @click="create()" style="margin-top: 4%">
        Create Order
      </v-btn>
    </v-form>


      <div class="footer">
        <p>Author</p>
        <p>&copy Urazov Vadim, JAVA DEVELOPER</p>
        <p>Metasearch platform that interacts with the search services of transport companies.
          The platform gathers ranked search results for a specific query from several search engines of companies,
          using linking algorithms, builds possible routes taking into account the parameters of transport trips,
          and finds among the received routes the optimal one in terms of time and cost.
          In addition, it is possible to purchase tickets for trips on the route selected by the user.</p>
      </div>
  </NuxtLayout>

</template>
<script>
import {mdiEye, mdiEyeOff} from '@mdi/js'


export default {

  data: () => ({
    mdiEye,
    mdiEyeOff,
    valid: false,
    idPath: 0,
    from: "",
    to: "",
    criteria: "",
    transport:"",
    dateFrom:"",
    tripRes:[],
    items: ['BUS', 'TRAIN', 'SHIP', 'ALL'],
    criteriaItems: ['PRICE','TIME']
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
    create() {
      this.$router.push('/login');
    },
    async getP(){
      this.tripRes=[

        {
          id:1,
          fromStation: 'Moskow',
          toStation: 'Anapa',
          transport: 'Train',
          price: '3500',
          duration: '04:00',
        },
        {
          id:2,
          fromStation: 'Moskow',
          toStation: 'Kazan',
          transport: 'SHIP',
          price: '300',
          duration: '08:00',
        },
        {
          fromStation: 'Kazan',
          toStation: 'Anapa',
          transport: 'SHIP',
          price: '300',
          duration: '08:00',
        },
        {
          id:3,
          fromStation: 'Moskow',
          toStation: 'Tomsk',
          transport: 'BUS',
          price: '200',
          duration: '07:00',
        },
        {
          fromStation: 'Tomsk',
          toStation: 'Kazan',
          transport: 'TRAIN',
          price: '300',
          duration: '04:00',
        },
        {
          fromStation: 'Kazan',
          toStation: 'Anapa',
          transport: 'SHIP',
          price: '300',
          duration: '08:00',
        },
      ];



    },
    async getPath() {
      var response = await $fetch('http://127.0.0.1:9080/graphql',
          {
            method: 'POST',
            body: {
              "query": "query{  " +
                  "getPaths(request:{\n" +
                  "      fromStation: \"" + this.fromStation + "\",\n" +
                  "      toStation: \"" + this.toStation + "\"\n" +
                  "      criteria: \"" + this.criteria + "\"\n" +
                  "      transport: \"" + this.transport + "\"\n" +
                  "      dateFrom: \"" + this.dateFrom+ "\"\n" +
                  "    }){\n" +
                  "       paths{\n" +
                  "       idPath\n "+
                  "       tripPath{ \n" +
                  "       fromStation\n" +
                  "       toStation\n" +
                  "       transport\n" +
                  "       price\n" +
                  "       duration\n" +
                  "      \n" +
                  "    }\n" +
                  " }"+
                  "}}", "variables": null
            },

          })
      var trips = response.data.getPaths.paths;
      for (let i = 0; i < trips.length; i++) {
        var id=trips[i].idPath;
        var pat=trips[i].tripPath;
        for (let j = 0; j <pat.length; j++) {
          var fr=pat[j].fromStation;
          var t=pat[j].toStation;
          var tr=pat[j].transport;
          var pr=pat[j].price;
          var d=pat[j].duration;
          this.tripRes.push({id,fr,t,tr,pr,d});
        }
      }
    }
  },
}

</script>
<style>
.card{
  padding: 2.5%;
}
td {
  padding: 10px;
  padding-left: 20px;
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
</style>