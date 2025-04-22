<template>
    <ion-header>
      <ion-toolbar color="primary">
        <ion-title>Factura de {{ customerName }}</ion-title>
        <ion-note slot="end">{{ today }}</ion-note>
      </ion-toolbar>
    </ion-header>
  </template>
  
  <script setup lang="ts">
  defineProps(['customerName'])
  const today = new Date().toLocaleDateString()
  </script>
  