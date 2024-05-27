import {defineNuxtPlugin} from '#app'
import {createVuetify} from 'vuetify'
import {aliases, mdi} from 'vuetify/lib/iconsets/mdi-svg'
// import {
//     VApp,
//     VAppBar,
//     VBtn,
//     VForm
// } from 'vuetify/components'
// Import everything
import * as components from 'vuetify/components'

export default defineNuxtPlugin((nuxtApp) => {
    const vuetify = createVuetify({
        icons: {
            defaultSet: 'mdi',
            aliases,
            sets: {
                mdi,
            }
        },
//           components: {
//               VApp,
//               VAppBar,
//               VBtn
//           }
        components: components

    })
    nuxtApp.vueApp.use(vuetify)
})
