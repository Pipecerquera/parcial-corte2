<template>
  <ion-page>
    <invoice-header :customer-name="customerName" />

    <ion-content class="ion-padding">
      <product-list @add-to-cart="addToCart" />

      <payment-methods @payment-selected="setPaymentMethod" />

      <ion-card>
        <ion-card-header>
          <ion-card-title>Resumen de Compra</ion-card-title>
        </ion-card-header>
        <ion-card-content>
          <p>Total de productos: {{ cart.length }}</p>
          <p>Total a pagar: ${{ total }}</p>
          <p>Método de pago: {{ paymentMethod }}</p>
        </ion-card-content>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import ProductList from '@/components/ProductList.vue'
import PaymentMethods from '@/components/PaymentMethods.vue'
import InvoiceHeader from '@/components/InvoiceHeader.vue'
import { ref, computed } from 'vue'

const customerName = 'Juan Pérez'
const cart = ref<any[]>([])
const paymentMethod = ref('Efectivo')

function addToCart(product: any) {
  cart.value.push(product)
}

function setPaymentMethod(method: string) {
  paymentMethod.value = method
}

const total = computed(() => cart.value.reduce((sum, p) => sum + p.price, 0))
</script>
