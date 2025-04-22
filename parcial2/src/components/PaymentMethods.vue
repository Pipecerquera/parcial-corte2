<template>
    <ion-list>
      <ion-radio-group v-model="selected" @ionChange="$emit('payment-selected', selected)">
        <ion-item>
          <ion-label>Efectivo</ion-label>
          <ion-radio slot="start" value="Efectivo"></ion-radio>
        </ion-item>
        <ion-item>
          <ion-label>Tarjeta</ion-label>
          <ion-radio slot="start" value="Tarjeta"></ion-radio>
        </ion-item>
      </ion-radio-group>
    </ion-list>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue'
  const selected = ref('Efectivo')
  defineEmits(['payment-selected'])
  </script>
  