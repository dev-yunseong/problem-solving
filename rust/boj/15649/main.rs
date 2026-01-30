use std::io;
use std::collections::HashSet;

fn main() {
    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("invalid input");

    let mut input = input.split_whitespace();
    
    let n = input.next().unwrap().parse::<i32>().unwrap();
    let m = input.next().unwrap().parse::<i32>().unwrap();

    solve(n, m);
}

fn solve(n: i32, m: i32) {
    let mut state = Vec::new();
    let mut used = HashSet::new();
    backtracking(&mut state, &mut used, n, m);
}

fn backtracking(state: &mut Vec<i32>, used: &mut HashSet<i32>, n: i32, m: i32) {
    if m == 0 {
        print(state);
        return;
    }

    for num in 1..=n {
        if used.contains(&num) { continue; }
        
        state.push(num);
        used.insert(num);
        backtracking(state, used, n, m - 1);
        state.pop();
        used.remove(&num);
    }
} 

fn print(state: &mut Vec<i32>) {
    for num in state {
        print!("{num} ");
    }
    println!();
}