<template>
  <NuxtLayout name="authenticated">
    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-col
              cols="12"
              md="4">
            <v-text-field
                v-model="fromStation"
                label="From station"
                required
            ></v-text-field>
          </v-col>
          <v-col
              cols="12"
              md="4">
            <v-text-field
                v-model="toStation"
                label="To station"
                required
            ></v-text-field>
          </v-col>
          <v-col>
            <v-text-field
                v-model="criteria"
                label="Criteria"
                required
            ></v-text-field>
          </v-col>
          <v-col
              cols="12"
              md="4">
            <v-text-field
                v-model="transport"
                label="Transport"
                required
            ></v-text-field>
          </v-col>
          <v-col
              cols="12"
              md="4">
            <v-text-field
                v-model="dateFrom"
                label="Date"
                required
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
      <v-btn class="mr-4" color="blue" height="50px" left="900px" width="150px" @click="getPath">
        Get
      </v-btn>
    </v-form>
    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
    &nbsp &nbsp &nbsp
    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
    &nbsp &nbsp &nbsp
    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp

    <v-table>
      <thead>
      <tr>
        <th class="text-left">
          From Station
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          To Station
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          Transport
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          Price
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          Duration Time
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp
          &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
          Tickets
        </th>

      </tr>
      </thead>
      <tbody>

      <tr v-for="subjects in trips">
        <ol type='I'>
          <li v-for="q in subjects">
            <td> {{ q.idPath }}</td>
            <td> {{ q.fromStation }}</td>
            <td>
              {{
                "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp "
              }}
            </td>
            <td> {{ q.toStation }}</td>
            <td>
              {{
                "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp "
              }}
            </td>
            <td>{{ q.transport }}</td>
            <td>
              {{
                "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp "
              }}
            </td>
            <td>{{ q.price }}</td>
            <td>
              {{
                "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp "
              }}
            </td>
            <td>{{ q.duration }}</td>
            <td>
              {{
                "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp "
              }}
            </td>
            <td>
              <v-btn class="mr-4" color="blue" height="50px" width="150px"
                     @click="create(q.transport)">
                Create Order
              </v-btn>
            </td>
          </li>
        </ol>
      </tr>
      </tbody>
    </v-table>
  </NuxtLayout>

</template>
<script>
import {mdiEye, mdiEyeOff} from '@mdi/js'

export default {

  data: () => ({
    mdiEye,
    mdiEyeOff,
    valid: false,
    fromStation: "",
    toStation: "",
    criteria: "",
    transport:"",
    dateFrom:"",
    trips: [[]]
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
    create(transport) {
      if (transport === 'BUS') {
        alert("BUS")
        location.href = 'http://192.168.0.109:3003/'
      } else if (transport === 'TRAIN') {
        alert("TRAIN")
        location.href = 'http://192.168.0.109:3004/'
      } else if (transport === 'SHIP') {
        alert("SHIP")
        location.href = 'http://192.168.0.109:3005/'
      }
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
      this.trips = response.data.getPaths.paths;

    }
  },
}

</script>
<style>
td {
  padding: 10px;
  padding-left: 20px;
}

</style>