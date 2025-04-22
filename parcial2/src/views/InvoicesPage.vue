<template>
    <ion-page>
      <ion-header>
        <ion-toolbar color="primary">
          <ion-title>Facturas Registradas</ion-title>
        </ion-toolbar>
      </ion-header>
  
      <ion-content class="ion-padding">
        <invoice-list :invoices="invoices" />
      </ion-content>
    </ion-page>
  </template>
  
  <script setup lang="ts">
  import InvoiceList from '@/components/InvoiceList.vue'
  import { ref, onMounted } from 'vue'
  
  const invoices = ref<any[]>([])
  const backendUrl = 'http://localhost:8080/api'
  
  onMounted(async () => {
    try {
      const res = await fetch(`${backendUrl}/invoices`)
      invoices.value = await res.json()
    } catch (error) {
      console.error('Error al cargar facturas:', error)
    }
  })
  </script>
  