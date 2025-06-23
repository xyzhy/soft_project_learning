<template>
    <div id="addWordPage">
        <div class="execution_panle">
            <el-button plain v-if="recover_idx != 0" @click="pre_word">上一个词</el-button>
            <el-button v-if="recover_idx < addition_numeric" @click="next_word">下一个词</el-button>
        </div>
        <div class="input_panle">
            <div>
                <div :class="{input: true, focus: isFocus == 0}" @click="confirm_focus(0)">
                    {{ input[0] }}
                </div>
                <div :class="{input: true, focus: isFocus == 1}" @click="confirm_focus(1)">
                    {{ input[1] }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { keyboard } from '@/remember_reuseablecode/event'

    export default {
        data() {
            return {
                wordBookId: '',
                vocab_size: 0,
                addition_numeric: 0,
                isFocus: 0,
                input: ['', ''],
                wait_sumbit: [{'english': '', 'means': '', wordBookId: ''}],
                recover_idx: 0,
            }
        },
        mounted() {
            /** 添加核心逻辑 */
            document.addEventListener('keydown', this.middim)
            this.wait_sumbit[0].wordBookId = this.wordBookId = this.$route.query.wordBookId
        },
        deactivated() {
            /** 移除核心逻辑 */
            document.removeEventListener('keypress', this.middim)
        },
        methods: {
            /** 提交词汇 */
            submit_vocab() {
                const sumbit_data = this.wait_sumbit.splice(0, this.addition_numeric)
                this.addition_numeric = this.recover_idx = 0
                batchAddVocab(sumbit_data)
                .then(resq => {
                    this.$message({
                        message: resq.msg,
                        type: 'success'
                    })
                })
                .catch(err => {
                    this.$message({
                        message: `${err.msg}\n请联系管理员`,
                        type: 'error'
                    })
                })
            },
            /** 核心逻辑中间函数 */
            middim(event) {
                keyboard(event, this, this.press_enter, this.entertab)
            },
            /** 确定聚焦 */
            confirm_focus(idx) {
                this.isFocus = idx
            },
            /** 按下 tab */
            entertab() {
                this.isFocus = this.isFocus == 0 ? 1 : 0    
            },
            /** 按下 enter */
            press_enter() {
                if (this.input[0].trim() === '' || this.input[1].trim() === '') {
                    this.$message({
                        message: '不能为空',
                        type: 'error'
                    })
                    return false
                }
                
                this.wait_sumbit[this.recover_idx].english = this.input[0]
                this.wait_sumbit[this.recover_idx].means = this.input[1]

                if (this.recover_idx == this.addition_numeric) {

                    this.wait_sumbit.push({
                        'wordBookId': this.wordBookId,
                        'english': '',
                        'means': ''
                    })
                    this.addition_numeric += 1
                    this.isFocus = 0
                    this.recover_idx += 1

                    this.input[0] = this.input[1] = ''
                } else {
                    this.next_word()
                }
                
            },
            /** 返回上一步词 */
            pre_word() {
                this.recover_idx -= 1
                this.input[0] = this.wait_sumbit[this.recover_idx].english
                this.input[1] = this.wait_sumbit[this.recover_idx].means
            },
            /** 下一个单词 */
            next_word() {
                this.recover_idx += 1
                this.input[0] = this.wait_sumbit[this.recover_idx].english
                this.input[1] = this.wait_sumbit[this.recover_idx].means
            },
        },
    }
</script>

<style scoped>
    #addWordPage {
        padding: 10px;
    }
    span {
        color: #fff;
        line-height: 2.4rem;
    }
    .button {
        width: 100%;
    }
    .execution_panle {
        display: flex;
        justify-content: space-between;
        position: relative;
        width: 100%;
        z-index: 1;
    }
    .input_panle {
        top: 0;
        position: absolute;
        width: 100%;
        background-color: rgb(44, 49, 77);
        height: 100%;
        left: 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .input_panle>div {
        text-align: center;
    }
    .input {
        margin: 0 auto;
        width: fit-content;
        min-width: 2rem;
        height: 4rem;
        margin-bottom: 2rem;
        border-bottom: 1px solid #fff;
        cursor: pointer;
        transition: 0.5s;
        font-size: 2rem;
        color: #fff;
    }
    .focus {
        border-bottom-color: #16fd2a;
    }
</style>