<template>
	<view>
		<button @click="connect">连接</button>
		<button @click="disconnect">断开</button>
		<button @click="subscribe">订阅</button>
		<button @click="unsubscribe">取消订阅</button>
		<input class="uni-input" v-model="msg" />
		<button @click="send">发送消息</button>
		<view v-for="(item, index) in dataList" :key="index">
			{{index}}:{{item}}
		</view>
	</view>
</template>

<script>
	import ws from '@/websocket/ws.js'
	import { refreshToken } from '@/config/config.js'
	export default {
		data() {
			return {
				msg: '{"name": "username"}',
				dataList: []
			}
		},
		onLoad() {
			// 模拟获取token，此步操作应在登录时去做
			refreshToken()
		},
		methods: {
			/**
			 * 创建连接
			 */
			connect() {
				ws.connect()
			},
			/**
			 * 断开连接
			 */
			disconnect(aa) {
				ws.disconnect()
			},
			/**
			 * 订阅
			 */
			subscribe() {
				ws.subscribe("/topic/say", this.message)
				ws.subscribe("/topic/callback", this.message)
			},
			/**
			 * 取消订阅
			 */
			unsubscribe() {
				ws.unsubscribe("/topic/say")
				ws.unsubscribe("/topic/callback")
			},
			/**
			 * 接收消息
			 * @param {Object} data
			 */
			message(data) {
				console.log(data)
				this.dataList.push(data.body)
			},
			/**
			 * 向服务端发送消息
			 */
			send() {
				ws.send('/welcome', this.msg)
			}
		}
	}
</script>

<style>
	.uni-input {
		height: 40px;
	}
</style>
