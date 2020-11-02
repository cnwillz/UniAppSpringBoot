<script>
	import ws from '@/websocket/ws.js'
	import { refreshToken } from '@/config/config.js'
	var f = {
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
				// this.dataList.push(data.body)
			},
			/**
			 * 向服务端发送消息
			 */
			send() {
				ws.send('/welcome', this.msg)
			}
		}
	}
	
	export default {
		onLaunch: function() {
		},
		onShow: function() {
			f.methods.connect()
			f.methods.subscribe()
		},
		onHide: function() {
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>
