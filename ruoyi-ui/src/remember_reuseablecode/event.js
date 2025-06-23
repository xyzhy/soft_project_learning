export function keyboard(event, vm, entercell, tabcell) {
    const code = event.key
    const ctrlKey = event.ctrlKey
    if (ctrlKey && code === 'r') {
        return true
    }

    const ch = event.key
    if (ch && ch.match(/^[a-zA-Z0-9 :;,]{1}$/)) {
        vm.input[vm.isFocus] += ch
        vm.$forceUpdate()
    } else if (ch === 'Backspace') {
        vm.input[vm.isFocus] = vm.input[vm.isFocus].slice(0, -1)
        vm.$forceUpdate()
    } else if (ch === 'Tab') {
        tabcell()
    } else if (ch === 'Enter') {
        entercell()
    }
    event.preventDefault()   
}