import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import Motorji from "../components/MotorjiView.vue";
import Uporabniki from "../components/UporabnikiView.vue";
import Rezervacije from "../components/RezervacijeView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/motorji",
    name: "motorji",
    component: Motorji,
  },
  {
    path: "/uporabniki",
    name: "uporabniki",
    component: Uporabniki,
  },
  {
    path: "/rezervacije",
    name: "rezervacije",
    component: Rezervacije,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
