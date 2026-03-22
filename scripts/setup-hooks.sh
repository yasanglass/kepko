#!/usr/bin/env bash

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
HOOKS_DIR="$PROJECT_DIR/.git/hooks"

if [[ ! -d "$PROJECT_DIR/.git" ]]; then
  echo "❌ Error: .git directory not found. Run this script from the repository root."
  exit 1
fi

mkdir -p "$HOOKS_DIR"

HOOKS=(pre-commit)

for HOOK in "${HOOKS[@]}"; do
  HOOK_SOURCE="$SCRIPT_DIR/hooks/$HOOK"
  HOOK_LINK="../../scripts/hooks/$HOOK"

  if [[ ! -f "$HOOK_SOURCE" ]]; then
    echo "❌ Error: hook source not found at scripts/hooks/$HOOK"
    exit 1
  fi

  chmod +x "$HOOK_SOURCE"
  ln -sfn "$HOOK_LINK" "$HOOKS_DIR/$HOOK"
  echo "✅ $HOOK hook installed: .git/hooks/$HOOK -> $HOOK_LINK"
done
